# NOTES

This app shows a list of users and their information from an api.
Here, the data is always fetched from the database for rendering and it is the single source of truth. 
Each screen is driven by a single viewmodel that can potentially contain business logic specific to that screen. The common denominator for all the viewModels is the database.
