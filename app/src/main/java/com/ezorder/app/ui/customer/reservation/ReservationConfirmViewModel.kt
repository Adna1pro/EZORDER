package com.ezorder.app.ui.customer.reservation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezorder.app.data.model.Reservation
import com.ezorder.app.data.model.ReservationStatus
import com.ezorder.app.data.repository.MockReservationRepository
import com.ezorder.app.data.repository.ReservationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// No auth system yet — every reservation is attributed to this placeholder
// user until Phase 9+ adds real accounts. Flagged, not a real decision.
private const val GUEST_USER_ID = "guest-user"

class ReservationConfirmViewModel(
    private val reservationRepository: ReservationRepository = MockReservationRepository()
) : ViewModel() {

    private val _confirmedReservation = MutableStateFlow<Reservation?>(null)
    val confirmedReservation: StateFlow<Reservation?> = _confirmedReservation.asStateFlow()

    fun confirmReservation(
        restaurantId: String,
        tableId: String,
        partySize: Int
    ) {
        viewModelScope.launch {
            val reservation = Reservation(
                id = "res-${System.currentTimeMillis()}",
                restaurantId = restaurantId,
                tableId = tableId,
                userId = GUEST_USER_ID,
                partySize = partySize,
                reservationDateTime = "2026-09-20T19:30", // hardcoded — real picker comes later
                status = ReservationStatus.PENDING,
                preOrderedItems = emptyList(), // cart attachment comes later
                specialRequests = ""
            )
            reservationRepository.createReservation(reservation)
            _confirmedReservation.value = reservation
        }
    }
}