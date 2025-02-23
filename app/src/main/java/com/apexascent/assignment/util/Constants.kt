package com.apexascent.assignment.util

import com.apexascent.assignment.R
import com.apexascent.assignment.data.TarotCard

object Constants {
    //I hardcoded the tarot deck for simplicity
    val tarotDeck = listOf(
        TarotCard(R.drawable.one_the_fool, "The Fool", listOf("Watch for new projects and new beginnings",
            "Prepare to take something on faith",
            "Something new comes your way; go for it"
        )),
        TarotCard(R.drawable.two_the_magician,"The Magician", listOf( "A powerful man may play a role in your day",
            "Your current situation must be seen as one element of a much larger plan"
        )),
        TarotCard(R.drawable.the_high_priestess,"The High Priestess", listOf("A mysterious woman arrives",
            "A sexual secret may surface",
            "Someone knows more than he or she will reveal"
        )),
        TarotCard(R.drawable.the_empress,"The Empress", listOf("Pregnancy is in the cards",
            "An opportunity to be involved in luxurious sexuality is coming",
            "Beware a tendency toward addiction"
        )),
        TarotCard(R.drawable.the_emperor,"The Emperor", listOf("A father figure arrives",
            "A new employer or authority figure will give you orders",
            "Expect discipline or correction in the near future"
        )),
        TarotCard(R.drawable.the_hierophant,"The Hierophant", listOf("Expect to be caught in a misdeed and punished accordingly",
            "Pray for forgiveness and confess wrongdoings",
            "A more experienced man, spiritual leader, or father figure will come into your life"
        )),
        TarotCard(R.drawable.the_lovers, "The Lovers", listOf("A new personal or professional relationship blossoms",
            "Sexual opportunities abound",
            "Unexpectedly, a friend becomes a lover"
        )),
        TarotCard(R.drawable.the_chariot,"The Chariot", listOf("Victory is a certainty",
            "Move ahead with all plans",
            "Beware the jealousy of others"
        )),
        TarotCard(R.drawable.strength,"Strength", listOf( "Your self-control will be tested",
            "A woman will seek to change her partner or lover",
            "You are a strong, capable person"
        )),
        TarotCard(R.drawable.the_hermit,"The Hermit", listOf( "A period of loneliness begins",
            "One partner in a relationship departs",
            "A search for love or money proves fruitless"
        )),
        TarotCard(R.drawable.wheel_of_fortune,"Wheel Of Fortune", listOf("Some events are in the hands of heaven",
            "You've lived through this before",
            "What happened then?"
        )),
        TarotCard(R.drawable.justice,"Justice", listOf("A legal verdict will be rendered soon",
            "Someone is making a decision",
            "You need to get the facts"
        )),
        TarotCard(R.drawable.the_hanged_man,"The Hanged Man", listOf("A traitor is revealed",
            "One of your friends is working against you",
            "Change your ways or suffer the consequences"
        )),
        TarotCard(R.drawable.death, "Death", listOf("A relationship or illness ends suddenly",
            "Limit travel and risk-taking",
            "General gloom and doom"
        )),
        TarotCard(R.drawable.temperance,"Temperance", listOf("Someone's using drugs or alcohol to excess",
            "It's time to get back on that diet"
        )),
        TarotCard(R.drawable.the_devil,"The Devil", listOf("Adultery and unfaithfulness",
            "A string of extremely bad luck is coming your way",
            "Beware evil influences and wolves in sheep's clothing"
        )),
        TarotCard(R.drawable.the_tower,"The Tower", listOf("Impending disaster",
            "Cancel plans and reverse decisions",
            "Someone wants to take you down a notch or two",
            "Don't hold back; say what you really mean"
        )),
        TarotCard(R.drawable.the_star,"The Star", listOf( "Get an astrology chart drawn up",
            "Someone is a little too starstruck",
            "What's happening now has long been fore-ordained"
        )),
        TarotCard(R.drawable.the_moon,"The Moon", listOf("Watch for problems at the end of the month",
            "Someone you know needs to howl at the moon more often",
            "Someone is about to change his or her mind about an important decision"
        )),
        TarotCard(R.drawable.the_sun,"The Sun", listOf(  "Everything's coming up roses (or sunflowers, whatever the case may be)",
            "Whatever's on your mind, go for it because you can't lose today"
        )),
        TarotCard(R.drawable.judgement,"Judgement", listOf( "An old issue you thought was over will come up again today",
            "Get ready for huge changes: break-ups, sudden calls from old friends, and unexpected setbacks",
            "God's trying to get your attention"
        )),
        TarotCard(R.drawable.the_world,"The World", listOf( "Winning the lottery",
            "Getting your heart's desire",
            "Having everything you ever imagined having"
        )),
        TarotCard(R.drawable.ace_of_wands,"Ace of Wands", listOf("Someone has the \"hots\" for you",
            "A new job offer is coming your way",
            "Walk softly, and carry a big stick"
        )),
        TarotCard(R.drawable.wands_two,"Two of Wands", listOf( "Beware false friends",
            "Don't be mealy-mouthed; say what you think and do what you want to do"
        )),
        TarotCard(R.drawable.wands_three,"Three of Wands", listOf("You'll be planning a trip soon",
            "Be on the lookout: your ship is coming in"
        )),
        TarotCard(R.drawable.wands_four,"Four of Wands", listOf("Someone is watching and evaluating your work",
            "You may get a wedding invitation soon"
        )),
        TarotCard(R.drawable.wands_five,"Five of Wands", listOf("Prepare for a fight with your best friend",
            "Remember: once you let words loose, you can't take them back")),
        TarotCard(R.drawable.wands_six,"Six of Wands", listOf("Someone is planning a party for you, but not everyone feels so good about your recent success",
            "Watch out for envious friends"
        )),
        TarotCard(R.drawable.wands_seven,"Seven of Wands", listOf("Don't be surprised by a personal attack",
            "Prepare to defend yourself or someone you love"
        )),
        TarotCard(R.drawable.wands_eight,"Eight of Wands", listOf("Watch for a surprising letter in the mail",
            "Your whole world is about to be turned on its ear"
        )),
        TarotCard(R.drawable.wands_nine,"Nine of Wands", listOf("Don't relax yet; there's more to come",
            "The test you're facing now is happening for one reason: to show you who your real friends are"
        )),
        TarotCard(R.drawable.wands_ten,"Ten of Wands", listOf("You're worn out",
            "Back off, take a time out, and let someone else handle things for a while"
        )),
        TarotCard(R.drawable.page_of_wands,"Page of Wands", listOf("This card represents a young man or woman with a fiery, enthusiastic demeanor, likely born a Cancer, Leo, or Virgo, who wants to start a new relationship with you")),
        TarotCard(R.drawable.knight_of_wands,"Knight of Wands", listOf("This card represents a man with a bold, passionate personality, likely born between July 12th and August 11th, who wants to sweep you off your feet"
        )),
        TarotCard(R.drawable.queen_of_wands,"Queen of Wands", listOf("This card represents a woman with an attractive, appealing personality, likely born between March 11th and April 20th, who wants to charm you into doing things her way"
        )),
        TarotCard(R.drawable.king_of_wands,"King of Wands", listOf( "This card represents an older man with a commanding, charismatic personality, likely born between November 13th and December 12th, who prefers to give directions and have them followed")),
        TarotCard(R.drawable.ace_of_swords,"Ace of Swords", listOf("The time to make a choice is now",
            "Stop wavering and do what you know is best"
        )),
        TarotCard(R.drawable.sword_two,"Two of Swords", listOf( "Sometimes, the only way to win is to refuse to fight",
            "You're stuck for now; let time pass before taking action"
        )),
        TarotCard(R.drawable.swords_three,"Three of Swords", listOf( "Breakups and infidelity abound",
            "What hurts now, though, will turn out to be good for you later on"
        )),
        TarotCard(R.drawable.swords_four,"Four of Swords", listOf( "Don't make any decision now",
            "Wait, and you'll be glad you did"
        )),
        TarotCard(R.drawable.swords_five,"Five of Swords", listOf(  "Someone is stealing from you, financially or romantically",
            "Be wary of friends who talk behind your back"
        )),
        TarotCard(R.drawable.swords_six,"Six of Swords", listOf( "You'll soon go on a long journey over water",
            "Actions have unexpected consequences, so be prepared"
        )),
        TarotCard(R.drawable.swords_seven,"Seven of Swords", listOf("Don't assume people around you are worthy of your trust",
            "Ask for an accounting of where people have been, and what they've been doing"
        )),
        TarotCard(R.drawable.swords_eight,"Eight of Swords", listOf("Get over playing the victim",
            "Once you realize you are your own biggest obstacle, nothing can hold you back"
        )),
        TarotCard(R.drawable.swords_nine,"Nine of Swords", listOf("If you take the action you're considering now, you'll be sorry in the future"
        )),
        TarotCard(R.drawable.swords_ten,"Ten of Swords", listOf("Disaster",
            "Put off plans and do not take action until omens are better"
        )),
        TarotCard(R.drawable.page_of_swords,"Page of Swords", listOf("This card represents a young man or woman with an airy, intellectual demeanor, likely born a Capricorn, Aquarius, or Pisces, who wants to learn something new from you or have a discussion with you"
        )),
        TarotCard(R.drawable.knight_of_swords,"Knight of Swords", listOf("A blunder leads someone to say something he or she regrets",
            "If this was you, be prepared to apologize and move on"
        )),
        TarotCard(R.drawable.queen_of_swords,"Queen of Swords", listOf("This card represents a woman with an artistic, intellectual nature, likely born between September 12th and October 12th, who uses clever, positive communication to sway others to her point of view"
        )),
        TarotCard(R.drawable.king_of_swords,"King of Swords", listOf("This card represents an older man with an insightful, deliberate spirit, likely born between May 11th and June 10th, who is known for his integrity and sharp decision-making ability"
        )),
        TarotCard(R.drawable.ace_of_pentacles,"Ace of Pentacles", listOf("Your health will improve",
            "The check you're looking for really is in the mail"
        )),
        TarotCard(R.drawable.pentacles_two,"Two of Pentacles", listOf("It's time to balance the budget",
            "Avoid the temptation to spend critical funds on frivolous goods"
        )),
        TarotCard(R.drawable.pentacles_three,"Three of Pentacles", listOf("A high-dollar contract is in your future",
            "If you work hard, you'll succeed"
        )),
        TarotCard(R.drawable.pentacles_four,"Four of Pentacles", listOf("A rainy day is coming\u2014it's time to save"
        )),
        TarotCard(R.drawable.pentacles_five,"Five of Pentacles", listOf("Finances are getting tighter",
            "Prepare for a setback"
        )),
        TarotCard(R.drawable.pentacles_six,"Six of Pentacles", listOf("When you need help, ask for it",
            "Remember, though: what you receive may be limited by what you've given to others in the past"
        )),
        TarotCard(R.drawable.pentacles_seven,"Seven of Pentacles", listOf("Things won't work out as expected",
            "Pick up the pieces and prepare to move on"
        )),
        TarotCard(R.drawable.pentacles_eight,"Eight of Pentacles", listOf( "Stop over-analyzing, researching, and outlining",
            "Buckle down and get the work done"
        )),
        TarotCard(R.drawable.pentacles_nine,"Nine of Pentacles", listOf("Until you appreciate what you have, you won't have any luck getting more"
        )),
        TarotCard(R.drawable.pentacles_ten,"Ten of Pentacles", listOf("Big money is in the near future",
            "Expect a powerful blessing to come your way"
        )),
        TarotCard(R.drawable.page_of_pentacles,"Page of Pentacles", listOf("This card represents a young man or woman with an earthy, practical demeanor, likely born an Aries, Taurus, or Gemini, who playfully encourages you to take financial or sexual risks"
        )),
        TarotCard(R.drawable.knight_of_pentacles,"Knight of Pentacles", listOf( "A stingy person may chide you for spending money",
            "Be prepared to defend an economic or sexual decision"
        )),
        TarotCard(R.drawable.queen_of_pentacles,"Queen of Pentacles", listOf("This card represents a woman with an expansive, sensual nature, likely born between December 13th and 31st, who uses sensual appeal and the promise of reward to sway others to her point of view"
        )),
        TarotCard(R.drawable.king_of_pentacles,"King of Pentacles", listOf("This card represents an older man with a financially, socially, and politically conservative spirit, likely born between August 12th and September 11th, who is known for putting his money where his mouth is"
        )),
        TarotCard(R.drawable.ace_of_cups,"Ace of Cups", listOf("Romance is in the cards",
            "A new relationship or marriage is just around the corner",
            "Prayers are answered"
        )),
        TarotCard(R.drawable.cups_two,"Two of Cups", listOf("Someone has a secret crush on you",
            "Relationships should be mutual; get rid of a leech"
        )),
        TarotCard(R.drawable.cups_three,"Three of Cups", listOf("Unconventional romance is coming your way: a love affair with someone you've always dismissed"
        )),
        TarotCard(R.drawable.cups_four,"Four of Cups", listOf("A lover is getting restless",
            "Find out what he or she needs, or new opportunities may lure your partner away"
        )),
        TarotCard(R.drawable.cups_five,"Five of Cups",  listOf( "A breakup looms",
            "Don't cry over spilt milk",
            "Take your lumps and get back in the saddle"
        )),
        TarotCard(R.drawable.cups_six,"Six of Cups", listOf( "A stingy spirit is strangling your enjoyment of life",
            "Loosen up and think of others for once, why don't you?"
        )),
        TarotCard(R.drawable.cups_seven,"Seven of Cups", listOf("You're being fed a line",
            "Rather than be dazzled by fancy words and promises, demand something real"
        )),
        TarotCard(R.drawable.cups_eight,"Eight of Cups", listOf("Someone's \"stepping out\" on you, now or in the near future",
            "Maybe it's time to quit talking about the problem and just move on"
        )),
        TarotCard(R.drawable.cups_nine,"Nine of Cups", listOf( "Whatever you want, you'll get it"
        )),
        TarotCard(R.drawable.cups_ten,"Ten of Cups", listOf("Marriage and family are in the cards",
            "Expect a friendship to blossom into a romance"
        )),
        TarotCard(R.drawable.page_of_cups,"Page of Cups", listOf("This card represents a young man or woman with a watery, dreamy demeanor, likely born a Libra, Scorpio, or Sagittarius, who wants to start a new relationship with you"
        )),
        TarotCard(R.drawable.knight_of_cups,"Knight of Cups", listOf("This card represents a man with an emotional, sensitive personality, likely born between October 13th and November 11th, who wants you to rally around his latest passionate cause"
        )),
        TarotCard(R.drawable.queen_of_cups,"Queen of Cups", listOf("This card represents a woman with an emotional, deeply spiritual nature, likely born between June 11th and July 11th, who uses emotional and spiritual appeals to sway others to her point of view"
        )),
        TarotCard(R.drawable.kings_of_cups,"King of Cups", listOf( "This card represents an older man with a gentle, sensitive presence, likely born between February 9th and March 10th, who is known for his fairness and tolerance"
        ))
    )
}