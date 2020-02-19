package com.ninovitale.employeecards.data

import com.ninovitale.employeecards.api.GistsApi
import com.ninovitale.employeecards.api.model.Gist
import io.reactivex.Single

interface GistsRepository {
    fun getGist(): Single<Gist>
}

class RemoteGistsRepository(private val remoteDataSource: RemoteDataSource) : GistsRepository {
    override fun getGist(): Single<Gist> = remoteDataSource.getGist()
}

//here the data source is responsible for retrieving the gist with a specific id, but ideally we could extend this to accept the id as input
class RemoteDataSource(private val api: GistsApi) {
    fun getGist(): Single<Gist> = api.getGist(GIST_ID)

    companion object {
        private const val GIST_ID = "41238222ac31fe36348544ee1d4a9a5e"
    }
}