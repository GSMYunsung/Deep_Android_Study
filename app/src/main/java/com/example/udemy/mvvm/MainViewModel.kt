package com.example.udemy.mvvm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.udemy.mvvm.models.FoodRecipe
import com.example.udemy.mvvm.util.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
    ) : AndroidViewModel(application) {

    //네트워크값이 항상 변할 수 있음
    var recipesResponse : MutableLiveData<NetWorkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String,String>) = viewModelScope.launch{
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        //처음에는 로딩 값으로 시작
        recipesResponse.value = NetWorkResult.Loading()
        if (hasInternetConnection()){
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)
            }catch (e:Exception){
                recipesResponse.value = NetWorkResult.Error("Recipes not found.")
            }
        }
        else{
            //만약 여기서 와이파이가 연결안됨 즉, (false) 값이라면 에러 메시지를 NetWorkResult class에 보내준다.
            recipesResponse.value = NetWorkResult.Error("No Internet Connection 연결오류")
        }
   }

    //api로 부터 응답을 받음
    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetWorkResult<FoodRecipe>? {
        when{
            response.message().toString().contains("timeout") -> {
                return NetWorkResult.Error("timeout")
            }
            response.code() == 402 ->{
                //402(결제 필요): 이 요청은 결제가 필요합니다.
                return NetWorkResult.Error("API key Limited.")
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetWorkResult.Error("Recipes not found.")
            }
            //만약 여기서 와이파이가 연결 즉, (true) 값이라면 데이터를 api에 값을 보내주는 RemoteDataSource에 보내준다.
            response.isSuccessful -> {
                val foodRecipes = response.body()
                return NetWorkResult.Success(foodRecipes!!)
            }
            else -> {
                return NetWorkResult.Error(response.message())
            }

        }
    }

    private fun hasInternetConnection() : Boolean{
            //디바이스의 네트워크 기능 받기
            val connectivityManager = getApplication<Application>().getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager

            //네트워크 연결
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            //네트워크 기능
            val capabilities = connectivityManager.getNetworkCapabilities(activityNetwork) ?: return false
            return when{
                //네트워크에 연결 되어있으면 true 연결되어있지 않으면 false
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
}