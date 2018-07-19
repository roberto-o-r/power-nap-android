package com.isscroberto.powernap.nap

import com.isscroberto.powernap.data.NapType
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NapPresenterTest {

    @Mock
    private lateinit var napView: NapContract.View

    private lateinit var napPresenter: NapPresenter

    @Before
    fun setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Get a reference to the class under test
        napPresenter = NapPresenter(napView, NapType.NAP_TYPE_POWER)
    }

    @Test
    fun createPresenter_setsThePresenterToView() {
        // Then the presenter is set to the view
        verify(napView).presenter = napPresenter
    }

    @Test
    fun createPresenter_startsPowerNap() {
        // Given a presenter initialized with PowerNap type.
        var napPresenter = NapPresenter(napView, NapType.NAP_TYPE_POWER)

        // When the presenter start the nap session.
        napPresenter.startNap()

        // The countdown starts.
        verify(napView).startCountdown(napPresenter.POWER_NAP_TIME)
    }

    @Test
    fun createPresenter_startsRefreshNap() {
        // Given a presenter initialized with Refresh type.
        var napPresenter = NapPresenter(napView, NapType.NAP_TYPE_REFRESH)

        // When the presenter start the nap session.
        napPresenter.startNap()

        // The countdown starts.
        verify(napView).startCountdown(napPresenter.REFRESH_NAP_TIME)
    }

}