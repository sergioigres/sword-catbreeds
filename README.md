# Sword Cat Breeds
This repository contains the Android codebase for Cat Breeds app.

## Modularization
Modules

:app

:feature:breeds -> CatsScreen | CatsViewModel | CatsUiState
:feature:breed -> CatScreen | CatViewModel | CatUiState
:feature:favorites ->

:data:cats -> CatsRepo | Cat | CatsLocalSource | CatsRemoteSource CatDatabaseModel CatApiModel
:data:favourites -> FavouritesRepo |  

:core:api

### UI Layer
https://developer.android.com/develop/ui/compose/tutorial
[**Compose documentation**](ui/core-compose/README.md)

### Data Layer
[**AppSettings**](app-settings/README.md)
[**Backend**](backend/README.md)
