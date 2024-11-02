package com.istudio.godiswithme.navigation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.istudio.godiswithme.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class TravelImage(
    val title: String,
    @DrawableRes val res: Int,
    val description: String
) : Parcelable

val travelImages = listOf(
    TravelImage(
        title = "Sigiriya",
        res = R.drawable.run,
        description = "Set off early, before the sun gets too hot and the crowds arrive. At the base of the rock, you’ll pass through a series of beautifully manicured gardens. There are the water gardens, complete with fountains and ponds where people used to bathe. Then there are the boulder gardens, named for the large rocks scattered across the sun-dappled grass - they were once the foundations of an ancient monastery, built around the third century BC. But despite the beauty of the gardens, your eyes will be perpetually drawn back to the looming silhouette of Sigiriya itself.\n" +
                "\n" +
                "Close to the top of the fortress, you’ll come across Lion’s Gate; a staircase with two huge stone lion paws on either side. There was once a lion’s face hewn into the rock as well, but it’s been eroded by the elements. This is how Sigiriya - which translates as ‘lion rock’ - got its name. Continue walking, and soon you’ll be at the top. This is where the palace once stood; you can still see the terraced palace gardens, and ancient walls and fortifications. The view over the surrounding jungle is incredible."
    ),
    TravelImage(
        title = "Sri lanka",
        res = R.drawable.logo,
        description = "Set off early, before the sun gets too hot and the crowds arrive. At the base of the rock, you’ll pass through a series of beautifully manicured gardens. There are the water gardens, complete with fountains and ponds where people used to bathe. Then there are the boulder gardens, named for the large rocks scattered across the sun-dappled grass - they were once the foundations of an ancient monastery, built around the third century BC. But despite the beauty of the gardens, your eyes will be perpetually drawn back to the looming silhouette of Sigiriya itself.\n" +
                "\n" +
                "Close to the top of the fortress, you’ll come across Lion’s Gate; a staircase with two huge stone lion paws on either side. There was once a lion’s face hewn into the rock as well, but it’s been eroded by the elements. This is how Sigiriya - which translates as ‘lion rock’ - got its name. Continue walking, and soon you’ll be at the top. This is where the palace once stood; you can still see the terraced palace gardens, and ancient walls and fortifications. The view over the surrounding jungle is incredible."
    ),
    TravelImage(
        title = "Himanshu's Trip",
        res = R.drawable.stop,
        description = "Set off early, before the sun gets too hot and the crowds arrive. At the base of the rock, you’ll pass through a series of beautifully manicured gardens. There are the water gardens, complete with fountains and ponds where people used to bathe. Then there are the boulder gardens, named for the large rocks scattered across the sun-dappled grass - they were once the foundations of an ancient monastery, built around the third century BC. But despite the beauty of the gardens, your eyes will be perpetually drawn back to the looming silhouette of Sigiriya itself.\n" +
                "\n" +
                "Close to the top of the fortress, you’ll come across Lion’s Gate; a staircase with two huge stone lion paws on either side. There was once a lion’s face hewn into the rock as well, but it’s been eroded by the elements. This is how Sigiriya - which translates as ‘lion rock’ - got its name. Continue walking, and soon you’ll be at the top. This is where the palace once stood; you can still see the terraced palace gardens, and ancient walls and fortifications. The view over the surrounding jungle is incredible."
    ),
    TravelImage(
        title = "Sri lanka's Sigiriya",
        res = R.drawable.danger,
        description = "Set off early, before the sun gets too hot and the crowds arrive. At the base of the rock, you’ll pass through a series of beautifully manicured gardens. There are the water gardens, complete with fountains and ponds where people used to bathe. Then there are the boulder gardens, named for the large rocks scattered across the sun-dappled grass - they were once the foundations of an ancient monastery, built around the third century BC. But despite the beauty of the gardens, your eyes will be perpetually drawn back to the looming silhouette of Sigiriya itself.\n" +
                "\n" +
                "Close to the top of the fortress, you’ll come across Lion’s Gate; a staircase with two huge stone lion paws on either side. There was once a lion’s face hewn into the rock as well, but it’s been eroded by the elements. This is how Sigiriya - which translates as ‘lion rock’ - got its name. Continue walking, and soon you’ll be at the top. This is where the palace once stood; you can still see the terraced palace gardens, and ancient walls and fortifications. The view over the surrounding jungle is incredible."
    ),
    TravelImage(
        title = "Paintings",
        res = R.drawable.danger,
        description = "The mirror wall when polished could once hold one's reflection on it. The design, architecture and the many ornate features of this beautiful fortress is an example of the luxury and the artistic taste that lay in the dynasty. The glamorous marvel of the ancient architectural world stands as a backdrop for the Hotel Sigiriya. The atmosphere in the entire area covered with greenery is a healing to the senses and an escape into a much simpler era of human existence."
    ),
    TravelImage(
        title = "Sigiriya",
        res = R.drawable.start,
        description = "The mirror wall when polished could once hold one's reflection on it. The design, architecture and the many ornate features of this beautiful fortress is an example of the luxury and the artistic taste that lay in the dynasty. The glamorous marvel of the ancient architectural world stands as a backdrop for the Hotel Sigiriya. The atmosphere in the entire area covered with greenery is a healing to the senses and an escape into a much simpler era of human existence."
    ),
    TravelImage(
        title = "Sigiriya's Top",
        res = R.drawable.stop,
        description = "The story of Sigiriya dates to the 14th century when it was used as a monastery. King Kashyapa, King Datusena’s eldest born son was talented in politics, war strategy and the arts as a prince ought to be. He was however, denied the throne and his younger step-brother King Mugallana who was the son of a royal consort was in line to be king. The vengeful Kashyapa hatched a plan with Migara, King Datusena’s army commander to rebel against his father.\n\n With Migara’s incitement and assistance, Prince Kashyapa overthrew King Datusena and usurped the throne. The former king was then imprisoned and later entombed into a wall. The end of the king is interpreted differently in various chronicles. \n\n King Kashyapa’s cruelty to his father and the repercussion of his action caused public rage and the disappointment and disgust of the priests. He was later referred to as the ‘Pithru Gathaka Kashyapa’ (Parricidal Kashyapa). The Prince Mugallana then fled to India.\n\nKing Kashyapa sought safety from the public fury and disgust and fled into a large, roughly lion-shaped rock that he then discovered. Thus, the kingdom of Sigiriya was born. The rock was elaborately designed with pools and lush green gardens and had dense forests protecting it from right round. The grand gardens of Sigiriya are said to be one of the world’s oldest landscaped gardens. The water system that remains a marvel and a mystery still function well during the rainy seasons and fills up the pools on all ground levels."
    ),
    TravelImage(
        title = "Loin Paw",
        res = R.drawable.stop,
        description = "The mirror wall stairway leads up to the ruins of the Sigiriya lion’s paws sculpture. The name “Sigiriya Lion Rock” comes from this royal palace entrance. The original entrance was through a massive brick sculpture of a lion head. Only the paws of this sculpture remain at the foot of the rock today. A rare Sigiriya mirror wall poem describes how imposing the lion sculpture was at the time of his visit (Sometime between the sixth to thirteenth centuries). The stairway that leads to the top of Sigiriya rock starts from where the lion statue’s mouth would have been."
    )
)