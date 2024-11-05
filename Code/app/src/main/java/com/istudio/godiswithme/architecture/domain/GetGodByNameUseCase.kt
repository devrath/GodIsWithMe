package com.istudio.godiswithme.architecture.domain

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.istudio.godiswithme.application.MY_APPLICATON_LOGS
import com.istudio.godiswithme.architecture.domain_entity.DescriptionData
import com.istudio.godiswithme.architecture.domain_entity.GodData
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.common.usecases.UseCaseFlowWithParam
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class GetGodByNameUseCase @Inject constructor(
    private val assetManager: AssetManager,
    private val logger: Logger
) : UseCaseFlowWithParam<String,GodData?> {

    companion object {
        const val ROOT_LOCATION = "inventory/gods"
        const val COVER_IMAGE_LOCATION = "cover/image.png"
        const val GOD_DESCRIPTION_LOCATION = "description.json"
    }

    override fun invoke(param: String): Flow<GodData?> = flow {
        // Collection to show in the grid of gods
        var godData: GodData? = null
        try {
            // Get list of folders
            val godFolders = assetManager.provide()?.list(ROOT_LOCATION).orEmpty()
            for (godFolder in godFolders) {
                loadGodData(assetManager, godFolder)?.let { god ->
                    if(god.godName == param){
                        godData =  god
                    }
                }
            }
        } catch (e: IOException) {
            logger.e(MY_APPLICATON_LOGS,e.message.orEmpty(),e)
        }

        emit(godData)
    }


    private fun loadGodData(providedAssetManager: AssetManager, godFolder: String): GodData? {
        val coverImagePath =  ROOT_LOCATION.plus("/").plus(godFolder).plus("/").plus(COVER_IMAGE_LOCATION)
        val descriptionPath = ROOT_LOCATION.plus("/").plus(godFolder).plus("/").plus(GOD_DESCRIPTION_LOCATION)

        val descriptionData = loadDescriptionData(providedAssetManager, descriptionPath) ?: return null
        val godImageBitmap = getImageBitMap(coverImagePath)
        val englishData = descriptionData.data.firstOrNull { it.languageCode == "en" }
        return GodData(
            godName = descriptionData.metaData.godName,
            languageCode = englishData?.languageCode.orEmpty(),
            language = englishData?.language.orEmpty(),
            description = englishData?.description.orEmpty(),
            godImageUri = coverImagePath,
            godImageBitmap = godImageBitmap
        )
    }

    private fun getImageBitMap(path: String): Bitmap? {
        return try {
            val inputStream = assetManager.provide()?.open(path)
            BitmapFactory.decodeStream(inputStream).also {
                inputStream?.close()
            }
        } catch (e: Exception) {
            logger.e(MY_APPLICATON_LOGS,e.message.orEmpty(),e)
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
            logger.e(MY_APPLICATON_LOGS,e.message.orEmpty(),e)
            null
        }


}