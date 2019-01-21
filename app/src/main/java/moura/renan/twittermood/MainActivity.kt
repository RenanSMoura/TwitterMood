package moura.renan.twittermood

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.cloud.language.v1.Document
import com.google.cloud.language.v1.LanguageServiceClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LanguageServiceClient.create().use { language ->

            // The text to analyze
            val text = "Hello, world!"
            val doc = Document.newBuilder()
                .setContent(text).setType(Document.Type.PLAIN_TEXT).build()

            // Detects the sentiment of the text
            val sentiment = language.analyzeSentiment(doc).documentSentiment

            System.out.printf("Text: %s%n", text)
            teste.text = String.format("Sentiment: %s, %s%n", sentiment.score, sentiment.magnitude)

        }
    }
}
