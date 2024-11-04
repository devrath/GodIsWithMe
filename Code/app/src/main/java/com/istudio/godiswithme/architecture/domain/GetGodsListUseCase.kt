package com.istudio.godiswithme.architecture.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.istudio.godiswithme.architecture.domain_entity.DescriptionData
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.common.usecases.UseCaseFlow
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class GetGodsListUseCase @Inject constructor(
    private val assetManager: AssetManager,
    private val logger: Logger
) : UseCaseFlow<List<GodData>> {

    override fun invoke(): Flow<List<GodData>> = flow {
        val listOfGods = mutableListOf<GodData>()

        assetManager.provide()?.let { providedAssetManager ->
            try {
                val godFolders = providedAssetManager.list("inventory/gods").orEmpty()
                godFolders.forEach { godFolder ->
                    val coverImagePath = "inventory/gods/$godFolder/cover/image.png"
                    val descriptionPath = "inventory/gods/$godFolder/description.json"

                    val godImageBitmap = loadBitmap(assetManager, coverImagePath)
                    val descriptionData = loadDescriptionData(assetManager, descriptionPath)

                    descriptionData?.let {
                        val englishData = it.data.firstOrNull { langData -> langData.languageCode == "en" }
                        val wrappedData = GodData(
                            godName = it.metaData.godName,
                            languageCode = englishData?.languageCode.orEmpty(),
                            language = englishData?.language.orEmpty(),
                            description = englishData?.description.orEmpty(),
                            godImage = godImageBitmap
                        )
                        listOfGods.add(wrappedData)
                    }
                }
            } catch (e: IOException) {
                logger.e("FETCH-JSON-DATA",e.message.toString(),e)
            }
        }

        emit(listOfGods)
    }

    private fun loadBitmap(assetManager: AssetManager, path: String): Bitmap? {
        return try {
            val inputStream = assetManager.provide()?.open(path)
            BitmapFactory.decodeStream(inputStream).also {
                inputStream?.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    private fun loadDescriptionData(assetManager: AssetManager, path: String): DescriptionData? =
        try {
            assetManager.provide()?.open(path).use { inputStream ->
                val reader = InputStreamReader(inputStream)
                Json.decodeFromString(reader.readText())
            }
        } catch (e: IOException) {
            logger.e("FETCH-JSON-DATA",e.message.toString(),e)
            null
        }
}
