package com.hado.githubrepositoriesinfinitescrolling

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class RepositoriesPagingSource(
    private val restInterface: RepositoriesApiService = DependencyContainer.repositoriesRetrofitClient
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val nextPage = params.key ?: 1
            val repos = restInterface.getRepositories(nextPage).repos
            LoadResult.Page(
                data = repos,
                prevKey = if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }
}