package com.istudio.godiswithme.architecture.data.repositoryimpl

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.istudio.godiswithme.application.APP_TAG
import com.istudio.godiswithme.architecture.domain.models.DescriptionData
import com.istudio.godiswithme.architecture.domain.models.GodData
import com.istudio.godiswithme.architecture.domain.models.Song
import com.istudio.godiswithme.architecture.domain.repository.GodRepository
import com.istudio.godiswithme.common.managers.AssetManager
import com.istudio.godiswithme.core.logger.applogger.local.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStreamReader

class GodRepositoryImpl(
    private val assetManager: AssetManager,
    private val logger: Logger
): GodRepository {

    override fun getGodData(godName: String): Flow<GodData?> = flow {
        try {
            val godData = assetManager.provide()
                ?.list(ROOT_LOCATION)
                .orEmpty()
                .mapNotNull { godFolder -> loadGodData(godFolder) }
                .firstOrNull { it.godName == godName }

            emit(godData)
        } catch (e: IOException) {
            logger.e(APP_TAG, e.message.orEmpty(), e)
            emit(null)
        }
    }


    override fun getGodList(languageCode: String): Flow<List<GodData>> = flow {
        try {
            val listOfGods = assetManager.provide()
                ?.list(ROOT_LOCATION)
                .orEmpty()
                .mapNotNull { godFolder -> loadGodData(godFolder) }

            emit(listOfGods)
        } catch (e: IOException) {
            logger.e(APP_TAG, e.message.orEmpty(), e)
            emit(emptyList())
        }
    }

    override fun getAudioList(godName: String): Flow<List<Song>> = flow {
        val descriptionPath = "$ROOT_LOCATION/$godName/$GOD_DESCRIPTION_LOCATION"
        val descriptionData = loadDescriptionData(descriptionPath)

        val data = descriptionData?.data?.firstOrNull { it.languageCode == "en" }

        val songsList = data?.songs?.map { song ->
            song.apply {
                songLocationUri = Uri.parse(this.songLocation)
            }
        } ?: emptyList()

        emit(songsList)
    }

    private fun loadGodData(godFolder: String): GodData? {
        val coverImagePath = "$ROOT_LOCATION/$godFolder/$COVER_IMAGE_LOCATION"
        val descriptionPath = "$ROOT_LOCATION/$godFolder/$GOD_DESCRIPTION_LOCATION"

        val godImageBitmap = getImageBitMap(coverImagePath) ?: return null
        val descriptionData = loadDescriptionData(descriptionPath) ?: return null

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
            assetManager.provide()?.open(path)?.use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            logger.e(APP_TAG, e.message.orEmpty(), e)
            null
        }
    }

    private fun loadDescriptionData(path: String): DescriptionData? = loadJsonData(path)

    private inline fun <reified T> loadJsonData(path: String): T? {
        return try {
            assetManager.provide()?.open(path).use { inputStream ->
                val reader = InputStreamReader(inputStream)
                Json.decodeFromString<T>(reader.readText())
            }
        } catch (e: IOException) {
            logger.e(APP_TAG, e.message.orEmpty(), e)
            null
        }
    }

    companion object {
        const val ROOT_LOCATION = "inventory/gods"
        const val COVER_IMAGE_LOCATION = "cover/image.png"
        const val GOD_DESCRIPTION_LOCATION = "description.json"
        const val GOD_SONGS_LOCATION = "songs"
    }

}