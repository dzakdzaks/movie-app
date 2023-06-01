package com.dzakdzaks.movieapp.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import com.dzakdzaks.movieapp.domain.usecase.FetchDetailMovieUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchImageMovieUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchReviewMoviesUseCase
import com.dzakdzaks.movieapp.domain.usecase.FetchTrailerMovieUseCase
import com.dzakdzaks.movieapp.MainDispatcherRule
import com.dzakdzaks.movieapp.common.model.movie.detail.MovieDetail
import com.dzakdzaks.movieapp.common.model.movie.image.MovieImage
import com.dzakdzaks.movieapp.common.model.movie.image.MovieImageBackdrop
import com.dzakdzaks.movieapp.common.model.movie.video.MovieVideo
import com.dzakdzaks.movieapp.common.model.movie.video.ResultMovieVideo
import com.dzakdzaks.movieapp.common.network.response.WrapperResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @MockK
    lateinit var fetchDetailMovieUseCase: FetchDetailMovieUseCase

    @MockK
    lateinit var fetchReviewMoviesUseCase: FetchReviewMoviesUseCase

    @MockK
    lateinit var fetchImageMovieUseCase: FetchImageMovieUseCase

    @MockK
    lateinit var fetchTrailerMovieUseCase: FetchTrailerMovieUseCase

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    lateinit var viewModel: DetailViewModel

    private val movieId = 1

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        coEvery {
            savedStateHandle.get<Int>(any())
        } returns movieId

        viewModel = DetailViewModel(
            fetchDetailMovieUseCase = fetchDetailMovieUseCase,
            fetchTrailerMovieUseCase = fetchTrailerMovieUseCase,
            fetchReviewMoviesUseCase = fetchReviewMoviesUseCase,
            fetchImageMovieUseCase = fetchImageMovieUseCase,
            savedStateHandle = savedStateHandle,
        )
    }

    @After
    fun after() {
        unmockkAll()
    }

    @Test
    fun testGetMovieDetail_success() = runTest {
        // given
        val expectedMovieDetail = MovieDetail(
            id = movieId,
            title = "The Shawshank Redemption",
            releaseDate = "1994-09-10",
            overview = "Andy Dufresne, a banker who is sentenced to life in Shawshank State Penitentiary for the murders of his wife and her lover, never gives up hope of being exonerated.",
            voteAverage = 9.3,
            posterPath = "/9ruuQhX9f6oZh95986J8PqKvUtY.jpg",
        )
        val slot = slot<Int>()
        coEvery {
            fetchDetailMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Success(data = expectedMovieDetail)

        // when
        viewModel.getMovieDetail(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchDetailMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(expectedMovieDetail, result.movieDetail)
        assertEquals(slot.captured, result.movieDetail.id)
        assertEquals(expectedMovieDetail.title, result.movieDetail.title)
        assertEquals("", result.movieDetailError)
        assertEquals(false, result.movieDetailLoading)
    }

    @Test
    fun testGetMovieDetail_error() = runTest {
        // given
        val errorMsg = "Error"
        val slot = slot<Int>()
        coEvery {
            fetchDetailMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Error(message = errorMsg)

        // when
        viewModel.getMovieDetail(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchDetailMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(MovieDetail(), result.movieDetail)
        assertEquals("Movie Detail: $errorMsg", result.movieDetailError)
        assertEquals(false, result.movieDetailLoading)
    }

    @Test
    fun testGetMovieTrailers_success() = runTest {
        // given
        val slot = slot<Int>()
        val expectedData = MovieVideo(
            id = movieId,
            results = listOf(
                ResultMovieVideo(
                    id = "63e6690a9512e1009309fd23",
                    key = "32RAq6JzY-w",
                    name = "Official Trailer",
                    type = "Trailer"
                )
            )
        )
        coEvery {
            fetchTrailerMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Success(data = expectedData)

        // when
        viewModel.getMovieTrailer(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchTrailerMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(expectedData.results, result.movieTrailers)
        assertEquals("", result.movieTrailerError)
        assertEquals(false, result.movieTrailerLoading)
    }

    @Test
    fun testGetMovieTrailers_error() = runTest {
        // given
        val errorMsg = "Error"
        val slot = slot<Int>()
        coEvery {
            fetchTrailerMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Error(message = errorMsg)

        // when
        viewModel.getMovieTrailer(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchTrailerMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(emptyList<ResultMovieVideo>(), result.movieTrailers)
        assertEquals("Movie Trailers: $errorMsg", result.movieTrailerError)
        assertEquals(false, result.movieTrailerLoading)
    }

    @Test
    fun testGetMovieTrailers_empty() = runTest {
        // given
        val errorMsg = "No trailer available."
        val slot = slot<Int>()
        coEvery {
            fetchTrailerMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Empty

        // when
        viewModel.getMovieTrailer(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchTrailerMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(emptyList<ResultMovieVideo>(), result.movieTrailers)
        assertEquals(errorMsg, result.movieTrailerError)
        assertEquals(false, result.movieTrailerLoading)
    }

    @Test
    fun testGetMovieImages_success() = runTest {
        // given
        val slot = slot<Int>()
        val expectedData = MovieImage(
            movieImageBackdrops = listOf(
                MovieImageBackdrop(
                    filePath = "/e2Jd0sYMCe6qvMbswGQbM0Mzxt0.jpg"
                )
            )
        )
        coEvery {
            fetchImageMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Success(data = expectedData)

        // when
        viewModel.getMovieImage(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchImageMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(expectedData.movieImageBackdrops, result.movieImages)
        assertEquals("", result.movieImageError)
        assertEquals(false, result.movieImageLoading)
    }

    @Test
    fun testGetMovieImages_error() = runTest {
        // given
        val errorMsg = "Error"
        val slot = slot<Int>()
        coEvery {
            fetchImageMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Error(message = errorMsg)

        // when
        viewModel.getMovieImage(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchImageMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(emptyList<MovieImageBackdrop>(), result.movieImages)
        assertEquals("Movie Images: $errorMsg", result.movieImageError)
        assertEquals(false, result.movieImageLoading)
    }

    @Test
    fun testGetMovieImages_empty() = runTest {
        // given
        val errorMsg = "No image available."
        val slot = slot<Int>()
        coEvery {
            fetchImageMovieUseCase.invoke(capture(slot))
        } returns WrapperResponse.Empty

        // when
        viewModel.getMovieImage(movieId)
        advanceUntilIdle()

        // then
        coVerify { fetchImageMovieUseCase.invoke(slot.captured) }
        val result = viewModel.state.value
        assertEquals(emptyList<MovieImageBackdrop>(), result.movieImages)
        assertEquals(errorMsg, result.movieImageError)
        assertEquals(false, result.movieTrailerLoading)
    }
}