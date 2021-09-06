package com.proway.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proway.coroutines.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * COROUTINES
         * Criamos um coroutine para esperar um timer de 3s
         * precisamos especificar o scope delas
         *
         * IO: para operações de entrada / saída de dados
         * Default: designado para operações que utilizam mais recursos de CPU
         * Main: utilizado em operações que manipulam componentes de UI
         * Uncofined: destinado para operações onde não existe a real necessidade
         * de serem executadas em thread específica
         */

        val retornoAsync = CoroutineScope(Dispatchers.Main).async {
            waitSplashScreenTimer()
        }

        /*
        Coroutine para executar o retorno do retornoAsync, dentro dela conseguimos colocar
        um await na variável
         */
        val job = CoroutineScope(Dispatchers.Main).launch {
            // Irá escutar o retorno da função -> waitSplashScreenTimer()
            val result = retornoAsync.await()
            // Só executará esta parte do código para baixo depois que retornar do waitSplashScreenTImer()
            if (result) {
                callNewActivity()
            }
        }
    }

    /*
    Usamos o suspend para propagar o suspend do delay()
    irá retornar somente depois de executar o delay e neste
    caso esperar os 3 segundos
     */
    private suspend fun waitSplashScreenTimer(): Boolean {
        delay(3000)
        return true
    }

    private fun callNewActivity() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }
    }
}