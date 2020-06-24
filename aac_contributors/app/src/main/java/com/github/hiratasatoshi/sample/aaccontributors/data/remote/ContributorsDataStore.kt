import android.util.Log
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.Contributor
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class ContributorsDataStore {
    companion object {
        const val TAG = "ContributorsDataStore"
        const val BASE_URL = "https://api.github.com/repos/googlesamples/android-architecture-components/";
    }
    interface  IContributorsApiService {
        @GET("contributors")
        fun getContributors(): Call<MutableList<Contributor>>
    }

    private fun apiClient() : IContributorsApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IContributorsApiService::class.java)
    }

    fun getContributors(): List<Contributor>? {
        val response = apiClient().getContributors().execute()
        //Log.d(TAG, "response body =${response.body()}")
        return response.body()
    }
}