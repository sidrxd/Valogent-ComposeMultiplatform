package screen.weapons

import cafe.adriel.voyager.core.model.ScreenModel
import connection.ApiHelper
import connection.ApiResult
import connection.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import model.WeaponsResponse
import utils.Coroutines

class WeaponsScreenModel : ScreenModel {

    val weapons = MutableStateFlow(ArrayList<WeaponsResponse.Data>())
    private val repository = MainRepository(ApiHelper.client)

    val selectedWeapon: MutableStateFlow<WeaponsResponse.Data>? = null

    fun getWeapons() {
        Coroutines.io {
            val result = repository.getWeapons()
            when (result) {
                is ApiResult.APIError -> {

                }

                is ApiResult.Error -> {

                }

                is ApiResult.Success -> {
                    weapons.emit(result.data.data)
                }
            }
        }
    }

    fun setSelectedWeapon(weapon: WeaponsResponse.Data) {
        Coroutines.io {
            selectedWeapon?.emit(weapon)
        }
    }
}