package com.ezorder.app.data.repository

import com.ezorder.app.data.model.Reservation

interface ReservationRepository {
    suspend fun getReservationsForUser(userId: String): List<Reservation>
    suspend fun createReservation(reservation: Reservation): Reservation
}

class MockReservationRepository : ReservationRepository {
    private val reservations = mutableListOf<Reservation>()

    override suspend fun getReservationsForUser(userId: String): List<Reservation> =
        reservations.filter { it.userId == userId }

    override suspend fun createReservation(reservation: Reservation): Reservation {
        reservations.add(reservation)
        return reservation
    }
}