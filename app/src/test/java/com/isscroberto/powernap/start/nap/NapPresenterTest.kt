package com.isscroberto.powernap.start.nap

import com.isscroberto.powernap.nap.NapContract
import com.isscroberto.powernap.nap.NapPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NapPresenterTest {

    private val NAP_TYPE = 1
    private val MILLISECONDS = 10000

    @Mock
    private lateinit var napView: NapContract.View

    private lateinit var napPresenter: NapPresenter

    @Before
    fun setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        napPresenter = NapPresenter(napView, NAP_TYPE)

        // Then the presenter is set to the view
        verify(napView).presenter = napPresenter
    }

    @Test
    fun startPresenter_loadConfigurationAndStartNap() {
        // Get a reference to the class under test
        napPresenter = NapPresenter(napView, NAP_TYPE)

        // When presenter starts.
        napPresenter.start()

        // Then the view starts the countdown with expected milliseconds.
        verify(napView).startCountdown(MILLISECONDS)
    }

}