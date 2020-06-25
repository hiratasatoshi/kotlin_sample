import android.util.Log
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.UserInfo
import com.github.hiratasatoshi.sample.aaccontributors.data.entity.ContributorInfo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException


class ContributorsDataStore {
    companion object {
        const val TAG = "ContributorsDataStore"
        const val BASE_URL = "https://api.github.com"
    }

    interface  IContributorsApiService {
        // AACリポジトリのcontributor一覧
        @GET("/repos/googlesamples/android-architecture-components/contributors")
        fun getContributors(): Call<MutableList<ContributorInfo>>

        // ユーザー詳細
        @GET("/users/{login}")
        fun getContributorDetail(@Path("login") login: String): Call<UserInfo>
    }

    private fun apiClient() : IContributorsApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IContributorsApiService::class.java)
    }

    fun getContributors(): List<ContributorInfo>? {
        try {
            val response = apiClient().getContributors().execute()
            return response.body()
        } catch (e: IOException) {
            Log.e(TAG, "$e")
        }
        return null

    }

    fun getContributorDetail(login: String): UserInfo? {
        try {
            val response = apiClient().getContributorDetail(login).execute()
            return response.body()
        } catch (e: IOException) {
            Log.e(TAG, "$e")
        }
        return null
    }
}