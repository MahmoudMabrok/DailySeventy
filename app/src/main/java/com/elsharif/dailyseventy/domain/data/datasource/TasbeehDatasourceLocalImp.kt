package com.elsharif.dailyseventy.domain.data.datasource

import android.util.Log
import com.elsharif.dailyseventy.domain.AppPreferences
import com.example.core.data.datasource.TasbeehDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TasbeehDatasourceLocalImp @Inject constructor(private val preferences: AppPreferences) : TasbeehDatasource {
    override fun increaseTasbeeh(): Flow<Boolean> = flow {
        Log.d("TAG", "increaseTasbeeh:")
        preferences.increaseTasbeeh()
        emit(true)
    }

    override fun resetTasbeeh(): Flow<Boolean> = flow {
        preferences.setTasbeeh(0)
        emit(true)
    }

    override fun getTasbeehCount(): Flow<Int> = preferences.tasbeehCounter
}