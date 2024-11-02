package com.istudio.godiswithme.navigation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.istudio.godiswithme.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class GodImage(
    val title: String,
    @DrawableRes val res: Int,
    val description: String
) : Parcelable

val godImages = listOf(
    GodImage(
        title = "Ganesha",
        res = R.drawable.run,
        description = "Ganesha, the beloved Hindu deity, is depicted as a figure with the head of an elephant and the body of a human. Known as the remover of obstacles and the god of wisdom, prosperity, and new beginnings, Ganesha is typically shown with four arms, though he can be depicted with more. Each of his arms holds symbolic items: an axe to cut attachments, a lotus for enlightenment, a sweet (modak) symbolizing reward for spiritual pursuit, and a broken tusk, a reminder of sacrifice for greater good.\n" +
                "\n" +
                "He is often portrayed seated in a calm, meditative pose or standing in a gentle, welcoming stance. His large ears signify listening to all prayers, while his round belly represents contentment and abundance. Ganesha’s distinctive trunk curves gracefully, and he often wears traditional Indian attire and a crown adorned with jewels. A tiny mouse, his vehicle, is usually shown near him, symbolizing humility and the ability to reach everywhere despite his own massive form. The image of Ganesha inspires protection, prosperity, and spiritual growth for devotees."
    ),
    GodImage(
        title = "Gayatri",
        res = R.drawable.logo,
        description = "The goddess Gayatri, often personified as the goddess of the Gayatri Mantra, is revered as the mother of all Vedas and embodies wisdom, purity, and enlightenment. She is depicted as a radiant goddess, typically shown seated on a lotus, symbolizing the awakened mind and purity. In her classic representation, Gayatri has five faces, each facing a different direction. These five faces symbolize the five elements (earth, water, fire, air, and ether) and five pranas (life forces) that sustain life, reflecting her cosmic power.\n" +
                "\n" +
                "She usually has ten arms, each holding symbolic objects like a conch (for the sound of creation), a discus (for time and preservation), a mace (for strength), a lotus (for purity), and other items representing protection and blessings. Her gentle expression radiates compassion and wisdom, and her presence is often accompanied by a vibrant aura, signifying divine light and the dawn of spiritual understanding. Worshiping Gayatri is believed to foster inner strength, wisdom, and clarity of thought, leading to spiritual growth and enlightenment."
    ),
    GodImage(
        title = "Parvati",
        res = R.drawable.stop,
        description = "Goddess Parvati, one of the principal deities in Hindu mythology, is revered as the goddess of love, fertility, devotion, and strength. She is the gentle and nurturing aspect of the divine feminine and is also known as Shakti, representing power and energy. Parvati is typically depicted as a beautiful, calm, and graceful figure, often shown seated or standing alongside her husband, Lord Shiva. Together, they embody the perfect balance of feminine and masculine energies.\n" +
                "\n" +
                "Parvati is usually depicted wearing traditional Indian attire, adorned with ornaments and a crown, symbolizing her royal and divine status. In many images, she is seen holding a lotus, representing purity, or a trident, reflecting her powerful connection to Shiva. Sometimes, she is shown with two or four arms, symbolizing her roles as a loving mother and a fierce protector. Parvati is also the mother of Ganesha and Kartikeya, embodying the ideal qualities of motherhood, compassion, and devotion.\n" +
                "\n" +
                "In her different forms, such as Durga and Kali, Parvati embodies strength and the fierce aspect of the divine feminine, protecting the world from evil. Her serene expression and powerful presence inspire devotion and reverence, symbolizing the gentle yet strong nature of the divine. Devotees pray to Parvati for blessings of harmony, prosperity, and spiritual growth."
    ),
    GodImage(
        title = "Lakshmi",
        res = R.drawable.danger,
        description = "Goddess Lakshmi, the Hindu goddess of wealth, prosperity, and abundance, is one of the most adored deities in Hinduism. She embodies beauty, fortune, and grace, and is often worshipped to bring material and spiritual prosperity. Lakshmi is traditionally depicted as a radiant, golden-skinned goddess seated or standing on a blooming lotus, symbolizing purity, beauty, and divine creation.\n" +
                "\n" +
                "She is typically shown with four arms, each representing an important aspect of human life: dharma (righteousness), artha (wealth), kama (desire), and moksha (liberation). In her hands, she holds lotus flowers, symbolizing spiritual enlightenment and growth, and a pot or bowl filled with gold coins, signifying abundance and generosity. Coins often flow from her hands, indicating her role in bestowing wealth and fortune upon her devotees.\n" +
                "\n" +
                "Lakshmi wears fine red and gold garments, adorned with jewels, which reflect her connection to wealth and royal status. She is often accompanied by white elephants, known as Gaja Lakshmi, who shower her with water, symbolizing the continuous flow of blessings. Her serene and compassionate expression radiates peace, while her graceful posture reflects calmness and benevolence. \n" +
                "\n" +
                "As the consort of Lord Vishnu, Lakshmi plays a vital role in maintaining balance and harmony in the universe. Worshiping Lakshmi is believed to bring prosperity, good fortune, and protection from misfortune. She is especially revered during Diwali, the festival of lights, when devotees pray for her blessings to bring light and success into their lives."
    ),
    GodImage(
        title = "Saraswati",
        res = R.drawable.danger,
        description = "Goddess Saraswati, the Hindu goddess of knowledge, wisdom, music, and arts, is revered as the source of all creative expression and learning. She embodies the pure essence of wisdom and is known as the patroness of learning and intellectual pursuits. Saraswati is often depicted as a serene and graceful figure dressed in a white sari, symbolizing purity and transcendence from material desires.\n" +
                "\n" +
                "Seated on a lotus or a white swan, Saraswati’s image reflects calmness and clarity. She usually has four arms, each holding items that signify different facets of knowledge and wisdom: a book (the Vedas, representing true knowledge), a mala (a rosary symbolizing spirituality), a pot of water (signifying purity and nourishment), and a veena (a musical instrument, symbolizing the harmony of the universe and her role as the goddess of music and arts).\n" +
                "\n" +
                "Her white swan, her chosen vehicle, symbolizes discernment, as it is believed to have the ability to separate milk from water, representing the ability to differentiate truth from illusion. In some representations, a peacock may be seen beside her, symbolizing the arts, but Saraswati remains detached, emphasizing knowledge over material allure.\n" +
                "\n" +
                "Saraswati is especially revered by students, artists, and scholars, who seek her blessings for wisdom, creativity, and success in their fields. Her presence inspires a calm and focused mind, guiding her devotees toward spiritual enlightenment and intellectual achievements. She is worshipped during festivals like Vasant Panchami, marking the onset of spring and celebrating the arts, learning, and wisdom."
    ),
    GodImage(
        title = "Vishnu",
        res = R.drawable.start,
        description = "Lord Vishnu, one of the principal deities in Hinduism, is known as the preserver and protector of the universe. Revered as the sustainer of cosmic order (dharma), Vishnu represents mercy, peace, and the stability necessary for life to flourish. He is part of the Trimurti, or the holy trinity, with Brahma (the creator) and Shiva (the destroyer), and is often worshipped as a symbol of divine compassion and protection.\n" +
                "\n" +
                "Vishnu is commonly depicted with a royal, serene demeanor, usually shown in a blue or dark complexion symbolizing boundless depth and the infinite nature of the universe. He typically has four arms, each holding a symbolic item: the conch (Shankha), representing the sacred sound \"Om\" and the cosmic life force; the discus (Sudarshana Chakra), symbolizing the mind and his role as protector of dharma; the mace (Gada), symbolizing strength and authority; and the lotus (Padma), representing purity and spiritual enlightenment.\n" +
                "\n" +
                "Vishnu is often shown reclining on the cosmic serpent, Shesha, floating on the ocean of cosmic milk, with his consort, Goddess Lakshmi, seated near him. This image signifies his role as the divine preserver, resting peacefully until the universe needs him. Vishnu descends to Earth in various avatars (incarnations), including Rama and Krishna, to restore balance when evil threatens the cosmic order.\n" +
                "\n" +
                "Worshiping Vishnu is believed to bring peace, protection, and prosperity. He is celebrated for his compassion and dedication to righteousness, inspiring devotees to live with integrity and harmony. His presence is especially honored during festivals like Vaikuntha Ekadashi, dedicated to achieving his blessings for a peaceful and righteous life."
    ),
    GodImage(
        title = "Srinivasa",
        res = R.drawable.stop,
        description = "Lord Srinivasa, also known as Venkateswara, Balaji, or Govinda, is a revered form of the Hindu god Vishnu, worshipped as the preserver of the universe and the deity of wealth, prosperity, and compassion. He is primarily worshipped at the famous Tirumala Venkateswara Temple in Tirupati, Andhra Pradesh, India, one of the most visited pilgrimage sites in the world. The name \"Srinivasa\" combines two words: \"Sri\" (Goddess Lakshmi, his consort) and \"Nivasa\" (abode), meaning \"the one in whom Lakshmi resides.\"\n" +
                "\n" +
                "Srinivasa is traditionally depicted as a majestic figure, standing tall with a calm, protective demeanor. He is adorned with lavish jewelry, a crown, and sacred marks on his body, reflecting his royal and divine status. His dark complexion symbolizes the vast, eternal nature of the cosmos. Srinivasa’s image typically has four arms, holding symbols of Lord Vishnu's power: the conch (Shankha) for the sacred sound of creation, the discus (Sudarshana Chakra) representing his protection of dharma, and the other two hands in blessing gestures, offering protection and prosperity to his devotees.\n" +
                "\n" +
                "A unique and endearing aspect of Srinivasa is the presence of a small mark of Lakshmi on his chest, representing her eternal presence with him. He is also known for his compassionate gaze and is believed to be deeply responsive to the devotion of his followers. His devotees believe that Srinivasa grants their wishes and bestows them with wealth, peace, and spiritual fulfillment.\n" +
                "\n" +
                "Srinivasa is celebrated during major festivals like Brahmotsavam and Vaikunta Ekadashi, where grand rituals are performed in his honor. Worshipping Srinivasa is believed to bring immense prosperity, relieve karmic burdens, and grant peace and blessings. His devotees chant \"Govinda! Govinda!\" as a powerful invocation, seeking his grace and guidance in their lives."
    ),
    GodImage(
        title = "Narasimha",
        res = R.drawable.stop,
        description = "Lord Narasimha, a fierce and protective form of Lord Vishnu, is one of his ten principal avatars and symbolizes the triumph of good over evil. Known as the \"man-lion\" deity, Narasimha combines the strength and ferocity of a lion with the wisdom and power of a god. This avatar was taken to destroy the demon king Hiranyakashipu, who had received a boon that made him nearly invincible. Narasimha embodies divine wrath in protection of his devotees and righteous principles (dharma).\n" +
                "\n" +
                "Narasimha is usually depicted with a human body and a lion’s face, featuring a fierce expression, sharp claws, and a powerful build. He is commonly shown emerging from a pillar, symbolizing his sudden and miraculous appearance. In his iconic image, Narasimha holds Hiranyakashipu on his lap, using his claws to vanquish the demon without violating the terms of the boon. His eyes are often wide and intense, and his mane and expression exude both fury and compassion, signifying his wrath toward evil and his boundless protection for devotees.\n" +
                "\n" +
                "Despite his terrifying form, Narasimha is also deeply compassionate toward his devotees, particularly Prahlada, the young prince who remained faithful to Vishnu despite his father Hiranyakashipu’s opposition. Narasimha’s appearance highlights the importance of unwavering devotion and the promise that divine protection will prevail over any threat. His figure often has four or more arms, wielding traditional symbols like the chakra (discus), shankha (conch), gada (mace), and sometimes an open hand offering blessings.\n" +
                "\n" +
                "Narasimha is worshipped for protection, courage, and the eradication of fear and evil. His devotees believe that invoking him can grant safety, strength, and support in times of adversity. Narasimha Jayanti, the festival celebrating his divine appearance, honors his powerful presence as a protector and is marked by prayers, rituals, and chants celebrating his courage and divine intervention in the face of overwhelming odds."
    )
)