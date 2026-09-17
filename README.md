# EZORDER — Phase 0: Project Initialization

**Discover. Reserve. Order Ahead. Enjoy.**

This is the Phase 0 deliverable from the EZORDER master spec: a clean,
buildable Android + Kotlin + Jetpack Compose project skeleton. It contains
no business logic and no real screens — that starts in Phase 1 (Design
System) and Phase 2 (Mock data layer).

## What's in this commit

- Gradle project (single `:app` module) using **Kotlin DSL** build files
- Kotlin 2.3.21 + Jetpack Compose (compiler via `org.jetbrains.kotlin.plugin.compose`)
- Compose BOM `2026.08.00`, Material 3
- `minSdk 26` / `targetSdk 37` / `compileSdk 37`
- Package structure exactly matching spec section 6:
  `data/{model,repository,mock}`, `domain/`, `ui/{theme,navigation,components}`,
  `ui/customer/{home,search,restaurant,menu,cart,checkout,reservation,orders,profile}`,
  `ui/restaurant/{dashboard,orders,reservations,tables,menu,settings}`, `utils/`
  (empty package dirs hold a `.gitkeep` so Git tracks them)
- A minimal `EZOrderTheme` (colors + type scale) — just enough to prove
  Compose renders with a branded look, not the full design-system token
  set (that's Phase 1)
- `MainActivity` rendering a single placeholder screen (app name + tagline)
  so you can confirm the whole toolchain actually builds and runs
- Standard `.gitignore`, JVM + instrumented test placeholders

## What's deliberately NOT in this commit

Per the spec's own build-order discipline (section 76/39) and prohibition
list (section 79/40), Phase 0 does **not** include:

- Any screens beyond the placeholder (Splash, Home, Search, etc. — Phase 3)
- Location permissions or logic (Phase 3)
- Google Maps / Places SDK dependency (Phase 8)
- Any data models, repositories, or mock data (Phase 2)
- Navigation graph / routes (added once there's more than one screen)
- Restaurant-owner app screens (Phase 9)
- Firebase, any backend, or real authentication (explicitly forbidden until requested)

## Versions and why

| Tool | Version | Notes |
|---|---|---|
| Android Gradle Plugin | **9.0.0** | Pinned to match what this Android Studio install supports — see below |
| Gradle | 9.1.0 | AGP 9.0.0's default paired Gradle version |
| Compose BOM | 2026.05.01 | Predates the 2026.08.00 BOM's bump to a required compileSdk 37 |
| compileSdk / targetSdk | 36 | Max API level AGP 9.0.0 supports (36.1) |
| minSdk | 26 (Android 8.0) | Reasonable modern floor; revisit if you need older-device reach |

Individual small libraries (core-ktx, lifecycle, activity-compose,
navigation-compose, espresso, etc.) are pinned to versions that were
current and mutually compatible as of this writing. If Android Studio's
dependency-upgrade check suggests newer patch releases for those later,
that's normal — just don't bump AGP/Compose BOM independently of each
other without checking compatibility.

### Why AGP is pinned to 9.0.0, not the newest release

AGP 9.3.0 (the actual current stable release as of writing) requires a
newer Android Studio build than what's installed here — Studio reported
this directly on sync: *"The project is using an incompatible version
(AGP 9.3.0) of the Android Gradle plugin. Latest supported version is AGP
9.0.0."* This isn't a macOS thing or a project-setup mistake; it's
strictly about which Android Studio release is on this machine, since AGP
is bundled with (and version-gated by) the IDE itself. Two ways to move
past this later:

- **Update Android Studio** (Android Studio → Check for Updates, or
  download the latest from <https://developer.android.com/studio>) and
  then raise AGP/Gradle/Compose BOM/compileSdk back up together, or
- **Stay on this Studio version** and keep building against AGP 9.0.0 —
  perfectly fine for a prototype; nothing in the spec requires the
  newest AGP.

If compileSdk 36 / SDK Build Tools 36.0.0 aren't installed yet, Android
Studio will prompt to install them via the SDK Manager on next sync —
accept that prompt.

### Why there's no `org.jetbrains.kotlin.android` plugin

AGP 9.0+ bundles its own Kotlin compiler and enables it by default
("built-in Kotlin") — you no longer apply the standalone
`org.jetbrains.kotlin.android` plugin at all. Applying it anyway (as an
earlier copy of this project did) fails Gradle sync with:

```
Cannot add extension with name 'kotlin', as there is an extension
already registered with that name.
```

because both AGP and the standalone plugin try to register the same
`kotlin` extension. The fix, per Google's own migration guide
(<https://developer.android.com/build/migrate-to-built-in-kotlin>), is to
remove the plugin rather than work around it — this project has no
legacy `kapt` or custom `kotlin.sourceSets{}` usage, so there was nothing
else to migrate. The same applies to `org.jetbrains.kotlin.plugin.compose`:
AGP's built-in Kotlin already supplies a Compose compiler that's
version-matched to itself once `buildFeatures.compose = true` is set, so
that plugin isn't declared either. `android.kotlinOptions { jvmTarget }`
is gone for the same reason — it now defaults to
`compileOptions.targetCompatibility` (17, set above) automatically.

If a future phase needs `kapt` (unlikely — prefer KSP) or Kotlin
Multiplatform, re-read that migration guide first; both interact with
built-in Kotlin differently than the old setup.

## One manual step required: the Gradle wrapper

This environment has no network access and no Android SDK, so it can't
download the Gradle wrapper JAR or verify a real compile. `gradle/wrapper/gradle-wrapper.properties`
is included (pinned to Gradle 9.5.0), but `gradle-wrapper.jar`,
`gradlew`, and `gradlew.bat` are **not** included.

To finish setup, do **one** of:

1. **Open the project in Android Studio.** It will detect the missing
   wrapper scripts and offer to generate them automatically on Gradle
   sync — this is the easiest path.
2. **Or, if you have Gradle installed locally**, run once from the
   project root:
   ```
   gradle wrapper --gradle-version 9.5.0
   ```
   This generates `gradlew`, `gradlew.bat`, and the wrapper jar.

After that, `./gradlew assembleDebug` should produce a working (if
extremely plain) EZORDER APK.

## Git

Initialize and make the first commit exactly as the spec's Git strategy
section recommends:

```
git init
git add .
git commit -m "Initial EZORDER project"
```

(Already done for you in this delivered copy — see `git log`.)

## Manual test checklist for Phase 0

- [ ] Project opens in Android Studio without sync errors (after wrapper generation)
- [ ] App builds and installs on an emulator or device (API 26+)
- [ ] Launcher icon appears (placeholder mark, not a real brand asset yet)
- [ ] App opens directly to the "EZORDER — Discover. Reserve. Order Ahead. Enjoy." placeholder screen
- [ ] No crash, no red screen, no lint/compile errors

## Next: Phase 1 — Design System

Once this builds cleanly, the next controlled prompt should be something like:

> "Implement the EZORDER design system (EZORDERColors, EZORDERTypography,
> EZORDERSpacing, EZORDERShapes) and the core reusable components
> (RestaurantCard, FoodCard, PrimaryButton, SearchBar, LoadingState,
> EmptyState, ErrorState) per spec section 57–58. Do not build any actual
> screens, navigation, Gradle changes, or data models yet."

Then test, then commit, then move to Phase 2 (mock data layer).
