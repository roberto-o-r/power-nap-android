package com.isscroberto.powernap.setup

import com.isscroberto.powernap.data.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SetupPresenterTest {

    @Mock
    private lateinit var setupView: SetupContract.View

    private lateinit var setupPresenter: SetupPresenter

    @Before
    fun setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Get a reference to the class under test
        setupPresenter = SetupPresenter(setupView)
    }

    @Test
    fun createPresenter_setsThePresenterToView() {
        // Then the presenter is set to the view
        verify(setupView).presenter = setupPresenter
    }

    @Test
    fun clickOnTypePower_showsDescription() {

        // When selecting a nap type.
        setupPresenter.selectNapType(NapType.NAP_TYPE_POWER)

        // The type is set.
        assertTrue(setupPresenter.napType == NapType.NAP_TYPE_POWER)

        // And the description of that type is showed in the view.
        verify(setupView).showDescription(NapType.NAP_TYPE_POWER)

    }

    @Test
    fun clickOnTypeRefresh_showsDescription() {

        // When selecting a nap type.
        setupPresenter.selectNapType(NapType.NAP_TYPE_REFRESH)

        // The type is set.
        assertTrue(setupPresenter.napType == NapType.NAP_TYPE_REFRESH)

        // The description of that type is showed in the view.
        verify(setupView).showDescription(NapType.NAP_TYPE_REFRESH)

    }

    @Test
    fun clickOnTypeRecharge_showsDescription() {

        // When selecting a nap type.
        setupPresenter.selectNapType(NapType.NAP_TYPE_RECHARGE)

        // The type is set.
        assertTrue(setupPresenter.napType == NapType.NAP_TYPE_RECHARGE)

        // The description of that type is showed in the view.
        verify(setupView).showDescription(NapType.NAP_TYPE_RECHARGE)

    }

    @Test
    fun clickOnTypeCoffee_showsDescription() {

        // When selecting a nap type.
        setupPresenter.selectNapType(NapType.NAP_TYPE_COFFEE)

        // The type is set.
        assertTrue(setupPresenter.napType == NapType.NAP_TYPE_COFFEE)

        // The description of that type is showed in the view.
        verify(setupView).showDescription(NapType.NAP_TYPE_COFFEE)

    }

}