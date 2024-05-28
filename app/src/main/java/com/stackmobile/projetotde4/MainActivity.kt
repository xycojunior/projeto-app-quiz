package com.stackmobile.projetotde4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.stackmobile.projetotde4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("Qual das seguintes é uma linguagem de programação orientada a objetos?", listOf("HTML", "CSS", "PHP", "SQL"), 2),
        Question(
            "O que significa HTML?",
            listOf("Hyper Text Markup Language", "Hyper Text Multiple Language", "Hyper Text Main Language", "High Tome Mala Layout"),
            0
        ),
        Question("Qual dos seguintes não é um tipo de dado em Python?", listOf("int", "string", "float", "Real"), 3),
        Question("Qual das opções a seguir é um loop em Python?", listOf("repeat", "while", "none", "until"), 1),
        Question("Qual dos seguintes é um operador lógico em muitas linguagens de programação?", listOf("&&", "||", "!", "Todos os anteriores"), 3),
        Question("Qual das seguintes linguagens é usada principalmente para desenvolvimento de aplicativos Android?", listOf("Ruby", "CSS", "Kotlin", "Swift"), 2),
        Question("Qual das seguintes é uma biblioteca popular de JavaScript para construir interfaces de usuário?", listOf("React", "Laravel", "Django", "Flask"), 0),
        Question("Qual das seguintes linguagens é conhecida por seu uso em ciência de dados e aprendizado de máquina?", listOf("PHP", "Python", "Ruby", "Java"), 1),
        Question("Qual é a saída do seguinte código Python? print(\"Hello, World!\")", listOf(" Hello, World!", "Olá Mundo!", "Oi Mundo", "Helloworld"), 0),
        Question("Qual é a função do comando print em Python?", listOf("Atribuir valores a variáveis", "Criar uma nova linha", "Imprimir valores na tela", "Verificar condições"), 2),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carregarQuestao()
        binding.botaoEnviar.setOnClickListener {
            checkResposta()
        }
    }

    private fun carregarQuestao() {
        val questao = questions[currentQuestionIndex]
        binding.questionTextView.text = questao.question
        binding.opcoesRadio.removeAllViews()
        questao.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.id = View.generateViewId()
            binding.opcoesRadio.addView(radioButton)
        }
        binding.feedbackTextView.visibility = View.GONE
    }

    private fun checkResposta() {
        val selectedOptionId = binding.opcoesRadio.checkedRadioButtonId
        if (selectedOptionId != -1) {
            val selectedIndex = binding.opcoesRadio.indexOfChild(findViewById(selectedOptionId))
            val question = questions[currentQuestionIndex]
            if (selectedIndex == question.correctAnswerIndex) {
                binding.feedbackTextView.text = "Correto!"
            } else {
                binding.feedbackTextView.text =
                    "Errado! A resposta correta é: ${question.options[question.correctAnswerIndex]}"
            }
            binding.feedbackTextView.visibility = View.VISIBLE

            // Atraso antes de carregar a próxima pergunta
            Handler(Looper.getMainLooper()).postDelayed({
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    carregarQuestao()
                } else {
                    binding.botaoEnviar.isEnabled = false
                    binding.feedbackTextView.text = "Quiz concluído!"
                }
            }, 2500)
        }
    }
}