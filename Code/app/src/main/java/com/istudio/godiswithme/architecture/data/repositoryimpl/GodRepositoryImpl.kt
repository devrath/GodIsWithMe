package com.istudio.godiswithme.architecture.data.repositoryimpl

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
    private val logger: Logger,
    private val context: Context
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
        val descriptionPath = constructPath(godName, GOD_DESCRIPTION_LOCATION)
        val songsPath = constructPath(godName, GOD_SONGS_LOCATION)

        val descriptionData = parseJsonDataToModel(descriptionPath)
        val data = mapGodDataWithLanguageCode(descriptionData, "en")

        val songsList = data?.songs?.map { song ->
            song.songLocation = songsPath.plus("/").plus(song.songLocation)
            song
        } ?: emptyList()

        emit(songsList)
    }


    /**
     * ******************************* Helper functions  *******************************************
     */
    /**
     * Get the god data in model w.r.t the god folder name
     */
    private fun loadGodData(godName: String): GodData? {
        val coverImagePath = constructPath(godName, COVER_IMAGE_LOCATION)
        val descriptionPath = constructPath(godName, GOD_DESCRIPTION_LOCATION)

        val godImageBitmap = getGodImageAsBitmapFromAssets(coverImagePath) ?: return null
        val descriptionData = parseJsonDataToModel(descriptionPath) ?: return null

        val data = mapGodDataWithLanguageCode(descriptionData, "en")

        return GodData(
            godName = descriptionData.metaData.godName,
            languageCode = data?.languageCode.orEmpty(),
            language = data?.language.orEmpty(),
            description = data?.description.orEmpty(),
            godImageUri = coverImagePath,
            godImageBitmap = godImageBitmap
        )
    }

    /**
     * Construct the path for base folder with root location and relative location
     */
    private fun constructPath(baseFolder: String, relativeLocation: String): String {
        return "$ROOT_LOCATION/$baseFolder/$relativeLocation"
    }

    /**
     * Retrieves a Bitmap image from the given asset file path.
     *
     * @param path The file path of the asset.
     * @return The Bitmap object, or null if an error occurs.
     */
    private fun getGodImageAsBitmapFromAssets(path: String): Bitmap? =
        runCatching {
            dataAsStream(path).use(BitmapFactory::decodeStream)
        }.onFailure { e ->
            logger.e(APP_TAG, e.message.orEmpty(), e)
        }.getOrNull()

    /**
     * Parses JSON data from the given file path into a DescriptionData object.
     *
     * @param path The file path containing the JSON data.
     * @return The parsed DescriptionData object, or null if an error occurs.
     */
    private fun parseJsonDataToModel(path: String): DescriptionData? {
        return try {
            dataAsStream(path).use { inputStream ->
                val reader = InputStreamReader(inputStream)
                Json.decodeFromString<DescriptionData>(reader.readText())
            }
        } catch (e: IOException) {
            logger.e(APP_TAG, e.message.orEmpty(), e)
            null
        }
    }

    /**
     * We pass the path of the file and get the file as steam of data
     */
    private fun dataAsStream(path: String) = assetManager.provide()?.open(path)

    /**
     * Get the God Data from list of god data based on language
     */
    private fun mapGodDataWithLanguageCode(descriptionData: DescriptionData?, languageCode: String) =
        descriptionData?.data?.firstOrNull { it.languageCode == languageCode }
    /**
     * ******************************* Helper functions  *******************************************
     */

    companion object {
        const val ROOT_LOCATION = "inventory/gods"
        const val COVER_IMAGE_LOCATION = "cover/image.png"
        const val GOD_DESCRIPTION_LOCATION = "description.json"
        const val GOD_SONGS_LOCATION = "songs"
    }

}