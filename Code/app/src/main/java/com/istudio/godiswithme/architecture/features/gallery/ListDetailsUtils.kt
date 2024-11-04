package com.istudio.godiswithme.architecture.features.gallery

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Place(
    val name: String,
    val image: String,
    val description: String
) : Parcelable

val placeLists = listOf(
    Place(
        name = "Sigiriya",
        image = "https://ichef.bbci.co.uk/images/ic/1920xn/p0b7n6dm.jpg.webp",
        description = "It is likely that the area around Sigiriya was inhabited since prehistoric times. There is clear evidence that the many rock shelters and caves in the vicinity were occupied by Buddhist monks and ascetics from as early as the 3rd century BC. The earliest evidence of human habitation at Sigiriya is the Aligala rock shelter to the east of Sigiriya rock, indicating that the area was occupied circa 3000 BCE during the Mesolithic Period.\n" +
                "\n" +
                "Buddhist monastic settlements were established during the 3rd century BC in the western and northern slopes of the boulder-strewn hills surrounding the Sigiriya rock. Several rock shelters or caves were created during this period. These shelters were made under large boulders, with carved drip ledges around the cave mouths. Rock inscriptions are carved near the drip ledges on many of the shelters, recording the donation of the shelters to the Buddhist monastic order as residences. These were made in the period between the 3rd century BCE and the 1st century CE.\n" +
                "\n" +
                "In 477 CE, Kashyapa I, the king’s son by a non-royal consort, seized the throne from King Dhatusena, following a coup assisted by Migara, the King’s nephew and army commander. The rightful heir, Moggallana, fearing for his life, fled to South India. Afraid of an attack by Moggallana, Kashyapa moved the capital and his residence from the traditional capital of Anuradhapura to the more secure Sigiriya. During King Kashyapa’s reign (477–495 CE), Sigiriya was developed into a complex city and fortress.[3][4] Most of the elaborate constructions on the rock summit and around it, including defensive structures, palaces, and gardens, date from this period.\n" +
                "\n" +
                "The Cūḷavaṃsa describes King Kashyapa as the son of King Dhatusena. Kashyapa murdered his father by walling him up alive and then usurping the throne which rightfully belonged to his half-brother Moggallana, Dhatusena's son by the true queen. Moggallana fled to India to escape being assassinated by Kashyapa, but vowed revenge. In India he raised an army with the intention of returning and retaking the throne of Sri Lanka, which he considered to be rightfully his. Expecting the inevitable return of Moggallana, Kashyapa is said to have built his palace on the summit of Sigiriya as a fortress as well as a pleasure palace. Moggallana finally arrived, declared war, and defeated Kashyapa in 495 CE. During the battle Kashyapa's armies abandoned him and he committed suicide by falling on his sword.\n" +
                "\n" +
                "The Cūḷavaṃsa and folklore inform us that the battle elephant on which Kashyapa was mounted changed course to take a strategic advantage, but the army misinterpreted the movement as the king's having opted to retreat, prompting the army to abandon him altogether. It is said that being too proud to surrender he took his dagger from his waistband, cut his throat, raised the dagger proudly, sheathed it, and fell dead. Moggallana returned the capital to Anuradhapura, converting Sigiriya into a Buddhist monastery complex,[5] which survived until the 13th or 14th century. After this period, no records are found on Sigiriya until the 16th and 17th centuries, when it was used briefly as an outpost of the Kingdom of Kandy."
    ),
    Place(
        name = "Nuara Eliya",
        image = "https://www.hindujagruti.org/hindi/wp-content/uploads/sites/2/2018/07/Ram-Sita-Laxman-Mandir-p.jpg",
        description = "It was the location where Sita, the wife of Rama, was held captive by Ravana after her abduction, also because she refused to stay in Ravana's palace and preferred to stay under shimshapa tree in Ashoka Vatika. It was here that Ravana's wife Mandodari came to meet her and also where Hanuman met her for the first time, and identified himself with the finger ring of Rama.\n" +
                "\n" +
                "\n" +
                "Ashoka tree (Saraca asoca), often known as Sita-Ashoka owing to its connection with the Ramayana\n" +
                "Sita stayed at Ashoka Vatika until the end of the epic battle between Rama and Ravana, which resulted in the destruction of Ravana himself and most of this clan. Much of the Ashoka Vatika was destroyed by Hanuman when he first visited Lanka searching for Sita. Also destroyed was the Pramda Vana at the centre of the Ashoka Vatika.[3]"
    ),
    Place(
        name = "Galle Fort ( Sri lanka)",
        image = "https://upload.wikimedia.org/wikipedia/commons/7/77/Galle_Fort.jpg",
        description = "Galle Fort (Sinhala: ගාලු කොටුව Galu Kotuwa; Tamil: காலிக் கோட்டை, romanized: Kālik Kōṭṭai), in the Bay of Galle on the southwest coast of Sri Lanka, was built first in 1588 by the Portuguese, then extensively fortified by the Dutch during the 17th century from 1649 onwards. It is a historical, archaeological and architectural heritage monument, which even after more than 432 years maintains a polished appearance, due to extensive reconstruction work done by the Archaeological Department of Sri Lanka.[1]\n" +
                "\n" +
                "The fort has a colourful history, and today has a multi-ethnic and multi-religious population.[2][3][4] The Sri Lankan government and many Dutch people who still own some of the properties inside the fort are looking at making this one of the modern wonders of the world.[4][5] The heritage value of the fort has been recognized by the UNESCO and the site has been inscribed as a cultural heritage UNESCO World Heritage Site under criteria iv, for its unique exposition of \"an urban ensemble which illustrates the interaction of European architecture and South Asian traditions from the 16th to the 19th centuries.\"[3]\n" +
                "\n" +
                "The Galle Fort, also known as the Dutch Fort or the \"Ramparts of Galle\", withstood the Boxing Day tsunami which damaged part of coastal area Galle town. It has since been restored.[2]"
    ),
)