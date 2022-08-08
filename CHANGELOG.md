# Change log

## [Unreleased]

- Fix crash due ProGuard minifying method used by viewBinding

## [0.4.10] - 2022-08-01

- Added delegates to simplify view binding

## [0.4.9] - 2022-07-20

- Added utils to log lifecycle events
- Added showToast
- Added findNavControllerInActivity & findNavControllerInFragment
- Updated deps
- Make .address to always return 8 characters 
- Added ppStr

## [0.4.8] - 2022-05-03

- Add extensions to convert Map<> to Bundle

## [0.4.7] - 2022-04-20

- Add shared prefs utils
- Add StateFlow at ThreeTenABPInitializer.initialized
- Add Flow.filter for readability

## [0.4.6] - 2022-04-12

- Add Flow.toList for testing

## [0.4.5] - 2022-04-11

- Fix issue with DataBinding when upgrade in the client
- Add new utils

## [0.4.4] - 2022-03-09

- Fix bug in OkHttp Authenticator (concurrent use)
- Add Tuple

## [0.4.3] - 2022-03-08

- Fix bug in OkHttp Authenticator (introduced in [0.4.2])
- Change HttpLoggingInterceptor from Network- to ApplicationInterceptor

## [0.4.2] - 2022-03-08

- Improve `createBearerAuthenticator()` (add `consent` param)

## [0.4.1] - 2022-03-08

- Change `Lifecycle.onEvent` API (it returns LifecycleObserver now)
- Add `Lifecycle.cancel` (to cancel onEvent action)

## [0.4.0] - 2022-03-08

- Change API of every* fns (breaking change)

## [0.3.2] - 2022-01-27

- Add lifecycle utils handling ON_DESTROY events
- Add more utils for dealing with disposables (wire-rxjava-android)
- Add a small logging util fn `Any.address`

## [0.3.1] - 2022-01-27

- Add Disposable.disposeOnViewDestroy (wire-rxjava-android)

## [0.3.0] - 2022-01-26

- Add ViewBindingAware
- Rename wire-coroutines-common & wire-coroutines to wire-coroutines & wire-coroutines-android
- Add Fragment.clearAdapterOnViewDestroy
- Add Disposable.addTo (wire-rxjava)

## [0.2.0] - 2022-01-06

- Add wire-retrofit
- Add wire-moshi
- Add wire-threeten
- Move to kts for scripting gradle configs

## [0.1.2] - 2021-12-15

- Add Memoize
- Refactorings & tweaks

## [0.1.1] - 2021-12-10

- Rename package ...firebaseconfig to firebase_config
- Rename wire-async-coroutines to wire-coroutines
- Add wire-firebase-messaging
- Add a couple of utils

## [0.1.0] - 2021-11-29

- Add wire-di-annotations
- Add wire-async-coroutines
- Add wire-initializer with a sample app
- Add wire-firebase-config with a sample app
- Add wire-utils


[Unreleased]: https://github.com/twobuffers/wire/compare/0.4.8...HEAD
[0.4.8]: https://github.com/twobuffers/wire/releases/tag/0.4.8
[0.4.7]: https://github.com/twobuffers/wire/releases/tag/0.4.7
[0.4.6]: https://github.com/twobuffers/wire/releases/tag/0.4.6
[0.4.5]: https://github.com/twobuffers/wire/releases/tag/0.4.5
[0.4.4]: https://github.com/twobuffers/wire/releases/tag/0.4.4
[0.4.3]: https://github.com/twobuffers/wire/releases/tag/0.4.3
[0.4.2]: https://github.com/twobuffers/wire/releases/tag/0.4.2
[0.4.1]: https://github.com/twobuffers/wire/releases/tag/0.4.1
[0.4.0]: https://github.com/twobuffers/wire/releases/tag/0.4.0
[0.3.2]: https://github.com/twobuffers/wire/releases/tag/0.3.2
[0.3.1]: https://github.com/twobuffers/wire/releases/tag/0.3.1
[0.3.0]: https://github.com/twobuffers/wire/releases/tag/0.3.0
[0.2.0]: https://github.com/twobuffers/wire/releases/tag/0.2.0
[0.1.2]: https://github.com/twobuffers/wire/releases/tag/0.1.2
[0.1.1]: https://github.com/twobuffers/wire/releases/tag/0.1.1
[0.1.0]: https://github.com/twobuffers/wire/releases/tag/0.1.0
