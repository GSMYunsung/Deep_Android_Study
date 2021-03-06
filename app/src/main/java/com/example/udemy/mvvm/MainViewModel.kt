package com.example.udemy.mvvm

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.*
import com.example.udemy.mvvm.data.RemoteDataSource
import com.example.udemy.mvvm.data.database.RecipesEntity
import com.example.udemy.mvvm.models.FoodRecipe
import com.example.udemy.mvvm.util.NetWorkResult
import com.example.udemy.viewModel.viewModelSample.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //이렇게 직접 이 리모트 함수를 가져올 수 도있지만 그렇게하면 의존성이 높아지기때문에 간접적으로 가져오게된다.
    //private val remoteapi : RemoteDataSource,
    private val repository: Repository,
    application: Application
    ) : AndroidViewModel(application) {

    /**Room 부분*/

    val readRecipes : LiveData<List<RecipesEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipes(recipesEntity)
        }

    /**레트로핏 부분*/

    //네트워크값이 항상 변할 수 있음
    var recipesResponse : MutableLiveData<NetWorkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String,String>) = viewModelScope.launch{
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        //처음에는 로딩 값으로 시작
        recipesResponse.value = NetWorkResult.Loading()
        Log.d("asdfadsfdsfd",hasInternetConnection().toString())
        if (hasInternetConnection()){
            try {
                //의존성 주입으로 Remote class에서 가져온 함수를 쓴다.
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data

                if(foodRecipe != null){
                    offlineCacheRecipes(foodRecipe)
                }

            }catch (e:Exception){
                recipesResponse.value = NetWorkResult.Error("Recipes not found.")
            }
        }
        else{
            //만약 여기서 와이파이가 연결안됨 즉, (false) 값이라면 에러 메시지를 NetWorkResult class에 보내준다.
            recipesResponse.value = NetWorkResult.Error("No Internet Connection 연결오류")
        }
   }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipesEntity = RecipesEntity(foodRecipe)
        insertRecipes(recipesEntity)

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