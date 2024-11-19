package com.example.use_case

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.model.Repo
import com.example.repository.RepoRepository

class GetPagingRepos(
    private val repository: RepoRepository
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = repository.getPagingRepos(
                pageSize = PAGE_SIZE,
                page = nextPageNumber
            )

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber.minus(
                    1
                ),
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2)
            .coerceAtLeast(0)
    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val PAGE_SIZE = 6
    }
}