package edu.sdsu.cs250.team5.road_watch

// Holds the repository the app uses. Defaults to in-memory (works on every platform).
// The desktop entry point swaps in the SQLite version for real persistence.
object HazardRepositoryProvider {
    var current: HazardRepository = InMemoryHazardRepository()
}