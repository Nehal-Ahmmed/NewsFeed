package com.nhbhuiyan.newsfeed.domain.usecase.app_entry

import com.nhbhuiyan.newsfeed.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}