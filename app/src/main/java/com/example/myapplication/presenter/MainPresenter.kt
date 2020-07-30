package com.example.myapplication.presenter

import android.content.Context
import com.example.myapplication.MainActivity
import com.example.myapplication.interactor.MainInteractor
import com.example.myapplication.interfaces.MainIInterface
import com.example.myapplication.interfaces.MainInterface
import com.example.myapplication.interfaces.MainPInterface
import org.json.JSONObject

class MainPresenter(view: MainActivity): MainPInterface {
    private var view: MainInterface = view
    private var interactor: MainIInterface = MainInteractor(this)


    override fun saveData(i: Int) {
        if (view != null){
            view.savedData(i)
        }
    }

    override fun storeData(json: JSONObject, context: Context) {
        if (interactor != null){
            interactor.storeData(json, context)
        }
    }

}