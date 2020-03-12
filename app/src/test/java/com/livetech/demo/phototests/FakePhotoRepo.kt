package com.livetech.demo.phototests

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.livetech.demo.core.PhotoRepo
import com.livetech.demo.core.models.PhotoData
import com.livetech.demo.core.models.PhotoResponse
import com.livtech.common.core.models.Resource
import com.livtech.common.core.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay

class FakePhotoRepo(scope: CoroutineScope, dispatcherProvider: DispatcherProvider) :
    PhotoRepo(scope, dispatcherProvider) {

    override fun getPhotos(params: HashMap<String, Any>): LiveData<Resource<PhotoData?>> {
        return liveData(scope.coroutineContext + dispatcherProvider.io()) {
            emit(Resource.Loading(null, "Loading"))
            delay(5000)
            emit(Resource.Success(Gson().fromJson(response, PhotoResponse::class.java).photos))
        }
    }

    private val response = "{\n" +
            "    \"photos\": {\n" +
            "        \"page\": 1,\n" +
            "        \"pages\": 66810,\n" +
            "        \"perpage\": 10,\n" +
            "        \"total\": \"668095\",\n" +
            "        \"photo\": [\n" +
            "            {\n" +
            "                \"id\": \"49651673807\",\n" +
            "                \"owner\": \"186656060@N02\",\n" +
            "                \"secret\": \"74df859d8d\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Amazon India Android App Now Lets You Shop Using Your Voice\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49650854633\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"2c7aae4bcf\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Senior Portrait San Antonio\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49651376571\",\n" +
            "                \"owner\": \"185735771@N03\",\n" +
            "                \"secret\": \"be406d5b6b\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Leaves\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49651650367\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"1485000ee6\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Senior Pictures San Antonio\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49651649222\",\n" +
            "                \"owner\": \"185735771@N03\",\n" +
            "                \"secret\": \"4a1c53a184\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Butterfly\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49650836323\",\n" +
            "                \"owner\": \"185735771@N03\",\n" +
            "                \"secret\": \"0bfdc42bfd\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Grass seeds\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49651366926\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"54103a13ac\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Emergency Glass Repair\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49650824873\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"07bd55759e\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Glass Repair West Palm Beach\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49650818053\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"c87db16aa0\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Miami Window Repair\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": \"49651628762\",\n" +
            "                \"owner\": \"187348477@N07\",\n" +
            "                \"secret\": \"d55ff302b0\",\n" +
            "                \"server\": \"65535\",\n" +
            "                \"farm\": 66,\n" +
            "                \"title\": \"Senior Photography San Antonio\",\n" +
            "                \"ispublic\": 1,\n" +
            "                \"isfriend\": 0,\n" +
            "                \"isfamily\": 0\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"stat\": \"ok\"\n" +
            "}"
}