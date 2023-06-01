package com.dzakdzaks.movieapp.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dzakdzaks.movieapp.domain.usecase.FetchReviewMoviesUseCase
import com.dzakdzaks.movieapp.common.model.movie.review.MovieReview
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse

class MovieReviewPagingSource(
    private val fetchReviewMoviesUseCase: FetchReviewMoviesUseCase,
    private val movieId: Int,
) : PagingSource<Int, MovieReview>() {
    override fun getRefreshKey(state: PagingState<Int, MovieReview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieReview> {
        val page = params.key ?: 1
        val result = fetchReviewMoviesUseCase(
            page = page,
            movieId = movieId,
        )

        return when (result) {
            is WrapperResponse.Success -> {
                val data = result.data
                LoadResult.Page(
                    data = data.results,
                    prevKey = null,
                    nextKey = if (result.data.results.isEmpty()) null else page + 1,
                )
            }

            is WrapperResponse.Error -> {
                LoadResult.Error(Exception(result.message))
            }

            is WrapperResponse.Empty -> {
                LoadResult.Error(Exception("There is no reviews available."))
            }
        }
    }
}