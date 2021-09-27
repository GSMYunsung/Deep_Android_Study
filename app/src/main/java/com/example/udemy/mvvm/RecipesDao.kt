package com.example.udemy.mvvm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    // API에서 새로운 레시피를 가져올시에 이전데이터 교체
    // 결론적으로 FoodRecipe Class 에서 값을 가져온다음 RoomDB에 Insert 한다.
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertRecipes(recipesEntity: RecipesEntity)

   @Query("SELECT * FROM recipes_table ORDER BY id ASC")
   fun readRecipes() : Flow<List<RecipesEntity>>

}