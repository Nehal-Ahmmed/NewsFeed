package com.nhbhuiyan.newsfeed.domain.usecase.app_entry

import com.nhbhuiyan.newsfeed.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}