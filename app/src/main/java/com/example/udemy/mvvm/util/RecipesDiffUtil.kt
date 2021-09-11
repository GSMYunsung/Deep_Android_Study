package com.example.udemy.mvvm.util

import androidx.recyclerview.widget.DiffUtil
import com.example.udemy.mvvm.models.Result

class RecipesDiffUtil(
    private val oldList : List<Result>,
    private val newList : List<Result>
) : DiffUtil.Callback() {

    //이전의 목록을 가져오고 기본적으로 주문 목록의 크기를 반환한다. 또한 크기를 반환한다.
    override fun getOldListSize(): Int {
       return oldList.size
    }

    //새 목록의 크기를 반환한 다음 항목을 동일하게 만든다.
    override fun getNewListSize(): Int {
        return newList.size
    }

    //두 목록이 동일한지 비교하고 확인
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    //동일한 내용은 두 항목에 동일한 데이터가 있는지 확인, UI에 따라 동작 변경가능
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}