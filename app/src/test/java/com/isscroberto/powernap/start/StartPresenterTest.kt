package com.isscroberto.powernap.start

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class StartPresenterTest {

    @Mock
    private lateinit var startView: StartContract.View

    private lateinit var startPresenter: StartPresenter

    @Before
    fun setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // The presenter won't update the view unless it's active.
        //Mockito.`when`(taskDetailView.isActive).thenReturn(true)
    }

    @Test
    fun createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        startPresenter = StartPresenter(startView)

        // Then the presenter is set to the view
        verify(startView).presenter = startPresenter
    }

    @Test
    fun startSession_showSessionSetup() {
        // Get a reference to the class under test
        startPresenter = StartPresenter(startView)

        // When the user starts a new session.
        startPresenter.startSession()

        // Then the view shows the setup
        verify(startView).navigateToSetup()
    }

}