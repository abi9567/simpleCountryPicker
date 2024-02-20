[![](https://jitpack.io/v/abi9567/simpleCountryPicker.svg)](https://jitpack.io/#abi9567/simpleCountryPicker)


## Usage

### Step 1. Add it in your root build.gradle at the end of repositories: ###

````kotlin 
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url ("https://jitpack.io") }
		}
	}
````

### Step 2. Add the dependency ###

````kotlin
 dependencies {
	        implementation ("com.github.abi9567:simpleCountryPicker:1.0.2")
	}
````

## Parameters Used for Customization

| Parameter                        | Description                                                                                                                                                                                                                                                                                                               |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pickedCountry                    | Returns the picked [CountryData]. You can use [CountryData.countryCode], [CountryData.countryIcon], [CountryData.countryName] from it. Where [CountryData.countryName] is @StringRes. Use [context.getString] to retrieve the string value.                                                                               |
| defaultCountryIdentifier         | Used for set initial country code and flag visible in the composable view. If not specifier the default country code shown will be India. for full list of available [defaultCountryIdentifier] check here [countryCodeIdentifiers](https://github.com/abi9567/simpleCountryPicker/blob/main/country_code_identifiers.md) |
| dialogTitle                      | The title of the country list dialog view. Default value is "Select Country".                                                                                                                                                                                                                                             |
| isCountryCodeVisible             | Set true or false for country code visibility in the composable view. The default value is true.                                                                                                                                                                                                                          |
| isCountryFlagVisible             | Set true or false for country flag visibility in the composable view. The default value is true.                                                                                                                                                                                                                          |
| isCircleShapeFlag                | Whether the flag shown in circle shape or rectangular. Default value is true (circle shape).                                                                                                                                                                                                                              |
| isEnabled                        | Set true for this view clickable. set false for not clickable. The default value is true.                                                                                                                                                                                                                                 |
| trailingIconComposable           | You can also change trailing icon by using this parameter - default it's null. If specify [trailingIconComposable] the default down arrow icon will not be visible, and this [trailingIconComposable] will be shown                                                                                                       |
| backgroundColor                  | background color of the dialog. Default [backgroundColor] is White.                                                                                                                                                                                                                                                       |
| textColor                        | Color of the country name & country code in the dialog. Default [textColor] is Black.                                                                                                                                                                                                                                     |
| countryCodeTextColorAndIconColor | Color of the country code & dropdown icon. Default [textColor] is Black.                                                                                                                                                                                                                                                  |
| stickyHeaderBackgroundColor      | Background Color of the sticky header.                                                                                                                                                                                                                                                                                    |
| dividerColor                     | Used for the divider color of dialog. It's default value is [DividerDefaults.color]                                                                                                                                                                                                                                       |
| searchFieldColors                | It is used for change the outlined search field colors it's type is [TextFieldColors] you can use [OutlinedTextFieldDefaults.colors] property to customize it's colors.                                                                                                                                                   |

