package com.knightshrestha.lightnovels.remotedatabase

import com.knightshrestha.lightnovels.remotedatabase.dataclass.NeonResponse


class MyRepo {
    suspend fun fetchAllData(): NeonResponse {
        return MyHelper.Api.fetchAllData()
    }
}