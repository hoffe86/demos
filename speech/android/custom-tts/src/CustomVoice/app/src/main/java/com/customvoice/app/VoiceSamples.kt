package com.customvoice.app

data class VoiceSample(
    val name: String,
    val description: String,
    val type: String,
    val ssml: String
)

class VoiceSamples {

    private var sample1: VoiceSample = VoiceSample(
        name = "Default",
        description = "Default Sample",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Es wird heute Nachmittag in Augsburg möglicherweise schneien. Es wird mäßiger Wind aus süd-westlicher Richtung wehen. Die Routen sind berechnet.</voice></speak>"
    )

    //Empathetic
    private var sample2: VoiceSample = VoiceSample(
        name = "E000007",
        description = "Es ist eine Entscheidung, die du immer wieder treffen musst, bis sie sich irgendwann richtig anfühlt.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Es ist eine Entscheidung, die du immer wieder treffen musst, bis sie sich irgendwann richtig anfühlt.</mstts:express-as></voice></speak>"
    )

    private var sample3: VoiceSample = VoiceSample(
        name = "E000029",
        description = "Deshalb ist Vergebung so mächtig, sie gibt uns innere Ruhe.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Deshalb ist Vergebung so mächtig, sie gibt uns innere Ruhe.</mstts:express-as></voice></speak>"
    )

    private var sample4: VoiceSample = VoiceSample(
        name = "E000093",
        description = "Jeder neue Tag bietet eine Chance, etwas Positives in die Welt zu bringen!",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Jeder neue Tag bietet eine Chance, etwas Positives in die Welt zu bringen!</mstts:express-as></voice></speak>"
    )

    private var sample5: VoiceSample = VoiceSample(
        name = "E000123",
        description = "Hat deine Reise gerade erst begonnen?",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Hat deine Reise gerade erst begonnen?</mstts:express-as></voice></speak>"
    )

    private var sample6: VoiceSample = VoiceSample(
        name = "E000143",
        description = "Die Intensität deines Kummers ist verständlich, zumal du eine starke Bindung und offensichtlich sehr schöne Freundschaft mit deinem Onkel hattest.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Die Intensität deines Kummers ist verständlich, zumal du eine starke Bindung und offensichtlich sehr schöne Freundschaft mit deinem Onkel hattest.</mstts:express-as></voice></speak>"
    )

    private var sample7: VoiceSample = VoiceSample(
        name = "E000157",
        description = "Sorry, es dauert noch einen Moment, bis ich auf das Adressbuch zugreifen kann.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Sorry, es dauert noch einen Moment, bis ich auf das Adressbuch zugreifen kann.</mstts:express-as></voice></speak>"
    )

    private var sample8: VoiceSample = VoiceSample(
        name = "E000188",
        description = "Und genau da komme ich ins Spiel.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Und genau da komme ich ins Spiel.</mstts:express-as></voice></speak>"
    )

    private var sample9: VoiceSample = VoiceSample(
        name = "E000210",
        description = "Das ist wichtig, vor allem nach stressigen Tagen.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Das ist wichtig, vor allem nach stressigen Tagen.</mstts:express-as></voice></speak>"
    )

    private var sample10: VoiceSample = VoiceSample(
        name = "E000325",
        description = "Absolut, sie gibt einem so viel zurück.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Absolut, sie gibt einem so viel zurück.</mstts:express-as></voice></speak>"
    )

    private var sample11: VoiceSample = VoiceSample(
        name = "E000400",
        description = "Vielleicht, wenn ich es aus der Ferne betrachten kann.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Vielleicht, wenn ich es aus der Ferne betrachten kann.</mstts:express-as></voice></speak>"
    )

    private var sample12: VoiceSample = VoiceSample(
        name = "E000425",
        description = "Hast du etwas ähnliches schon früher gesehen?",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Hast du etwas ähnliches schon früher gesehen?</mstts:express-as></voice></speak>"
    )

    private var sample13: VoiceSample = VoiceSample(
        name = "E000608",
        description = "Verabschiedet euch von strikten Plänen und Vorstellungen und stürzt euch ins Abenteuer.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Verabschiedet euch von strikten Plänen und Vorstellungen und stürzt euch ins Abenteuer.</mstts:express-as></voice></speak>"
    )

    private var sample14: VoiceSample = VoiceSample(
        name = "E000624",
        description = "Ich muss die Mail gelöscht haben, ohne sie abzuspeichern.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Ich muss die Mail gelöscht haben, ohne sie abzuspeichern.</mstts:express-as></voice></speak>"
    )

    private var sample15: VoiceSample = VoiceSample(
        name = "E000656",
        description = "Du bist jemand, der klare Prinzipien hat, und das ist selten.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Du bist jemand, der klare Prinzipien hat, und das ist selten.</mstts:express-as></voice></speak>"
    )

    private var sample16: VoiceSample = VoiceSample(
        name = "E000690",
        description = "Egal welcher Verlust, es schmerzt immer.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Egal welcher Verlust, es schmerzt immer.</mstts:express-as></voice></speak>"
    )

    private var sample17: VoiceSample = VoiceSample(
        name = "E000692",
        description = "Es ist immer schön, wenn sich die harte Arbeit auszahlt, das motiviert.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Es ist immer schön, wenn sich die harte Arbeit auszahlt, das motiviert.</mstts:express-as></voice></speak>"
    )

    private var sample18: VoiceSample = VoiceSample(
        name = "E000767",
        description = "Es ist wunderbar zu sehen, wie du und dein Partner trotz eurer Unterschiede harmonisch zusammenleben und voneinander lernt, das zeugt von gegenseitigem Respekt und Wertschätzung.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Es ist wunderbar zu sehen, wie du und dein Partner trotz eurer Unterschiede harmonisch zusammenleben und voneinander lernt, das zeugt von gegenseitigem Respekt und Wertschätzung.</mstts:express-as></voice></speak>"
    )

    private var sample19: VoiceSample = VoiceSample(
        name = "E000821",
        description = "Leider konnte ich keine Audioquellen für HD Radio finden.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Leider konnte ich keine Audioquellen für HD Radio finden.</mstts:express-as></voice></speak>"
    )

    private var sample20: VoiceSample = VoiceSample(
        name = "E000851",
        description = "Du darfst auch Schwächen zeigen, und das macht dich nicht weniger beeindruckend.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Du darfst auch Schwächen zeigen, und das macht dich nicht weniger beeindruckend.</mstts:express-as></voice></speak>"
    )

    private var sample21: VoiceSample = VoiceSample(
        name = "E000864",
        description = "Du bist unglaublich wissbegierig, weil du immer nach neuen Erkenntnissen strebst und nie aufhörst, dich weiterzubilden.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Du bist unglaublich wissbegierig, weil du immer nach neuen Erkenntnissen strebst und nie aufhörst, dich weiterzubilden.</mstts:express-as></voice></speak>"
    )

    private var sample22: VoiceSample = VoiceSample(
        name = "E000876",
        description = "Das klingt, als wäre sie voller persönlicher Favoriten, die dich immer wieder aufrichten.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Das klingt, als wäre sie voller persönlicher Favoriten, die dich immer wieder aufrichten.</mstts:express-as></voice></speak>"
    )

    private var sample23: VoiceSample = VoiceSample(
        name = "E000926",
        description = "Ich schätze deine aufrichtige Art, weil du immer ehrlich bist und deine Worte und Taten im Einklang mit deinen Überzeugungen stehen.",
        type = "Empathic",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='empathetic' styledegree='2'>Ich schätze deine aufrichtige Art, weil du immer ehrlich bist und deine Worte und Taten im Einklang mit deinen Überzeugungen stehen.</mstts:express-as></voice></speak>"
    )

    //General
    private var sample24: VoiceSample = VoiceSample(
        name = "G000149",
        description = "Leider ist das Setzen der Fußraumtemperatur in diesem Fahrzeug nicht verfügbar.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Leider ist das Setzen der Fußraumtemperatur in diesem Fahrzeug nicht verfügbar.</voice></speak>"
    )

    private var sample25: VoiceSample = VoiceSample(
        name = "G000152",
        description = "Aus Sicherheitsgründen kann der Airbag leider nicht per Sprache bedient werden.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Aus Sicherheitsgründen kann der Airbag leider nicht per Sprache bedient werden.</voice></speak>"
    )

    private var sample26: VoiceSample = VoiceSample(
        name = "G000184",
        description = "Bitte schalte die Zündung ein, um die Funktion bedienen zu können.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Bitte schalte die Zündung ein, um die Funktion bedienen zu können.</voice></speak>"
    )

    private var sample27: VoiceSample = VoiceSample(
        name = "G000233",
        description = "Ich stelle die Fußraumtemperatur vorne links auf die niedrigste Stufe.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Ich stelle die Fußraumtemperatur vorne links auf die niedrigste Stufe.</voice></speak>"
    )

    private var sample28: VoiceSample = VoiceSample(
        name = "G000235",
        description = "Ich kann das Innenlicht auf der Fahrerseite gerade nicht bedienen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Ich kann das Innenlicht auf der Fahrerseite gerade nicht bedienen.</voice></speak>"
    )

    private var sample29: VoiceSample = VoiceSample(
        name = "G000261",
        description = "Alles klar, ich schalte die Sitzbelüftung für den Fahrersitz ein.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Alles klar, ich schalte die Sitzbelüftung für den Fahrersitz ein.</voice></speak>"
    )

    private var sample30: VoiceSample = VoiceSample(
        name = "G000392",
        description = "Ich schalte die Massage für den linken Sitz an.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Ich schalte die Massage für den linken Sitz an.</voice></speak>"
    )

    private var sample31: VoiceSample = VoiceSample(
        name = "G000400",
        description = "Alles klar, die Massage ist jetzt hinten links aktiv.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Alles klar, die Massage ist jetzt hinten links aktiv.</voice></speak>"
    )

    private var sample32: VoiceSample = VoiceSample(
        name = "G000417",
        description = "Verstanden, ich schalte den Geschwindigkeitswarnton für diese Fahrt ein.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Verstanden, ich schalte den Geschwindigkeitswarnton für diese Fahrt ein.</voice></speak>"
    )

    private var sample33: VoiceSample = VoiceSample(
        name = "G000460",
        description = "Verstanden, Fenster hinten rechts wird ein Stück geschlossen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Verstanden, Fenster hinten rechts wird ein Stück geschlossen.</voice></speak>"
    )

    private var sample34: VoiceSample = VoiceSample(
        name = "G000469",
        description = "Mit den aktuellen Sirius X M Einstellungen sind anstößige Inhalte blockiert.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Mit den aktuellen Sirius X M Einstellungen sind anstößige Inhalte blockiert.</voice></speak>"
    )

    private var sample35: VoiceSample = VoiceSample(
        name = "G000651",
        description = "Entschuldigung, ich kann den Fernsehsender gerade nicht speichern.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Entschuldigung, ich kann den Fernsehsender gerade nicht speichern.</voice></speak>"
    )

    private var sample36: VoiceSample = VoiceSample(
        name = "G000678",
        description = "Verstanden, Beduftung wird auf höchste Stufe gestellt.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Verstanden, Beduftung wird auf höchste Stufe gestellt.</voice></speak>"
    )

    private var sample37: VoiceSample = VoiceSample(
        name = "G000804",
        description = "Das Ladelimit kann ich gerade nicht bedienen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Das Ladelimit kann ich gerade nicht bedienen.</voice></speak>"
    )

    private var sample38: VoiceSample = VoiceSample(
        name = "G000871",
        description = "Oh je, dazu finde ich keine E-Mail-Adressen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Oh je, dazu finde ich keine E-Mail-Adressen.</voice></speak>"
    )

    private var sample39: VoiceSample = VoiceSample(
        name = "G000888",
        description = "Alles klar, Schiebedach wird zur Hälfte geschlossen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Alles klar, Schiebedach wird zur Hälfte geschlossen.</voice></speak>"
    )

    private var sample40: VoiceSample = VoiceSample(
        name = "G000951",
        description = "Möchtest du mit dem Fahrzeug-Connect verbunden werden?",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Möchtest du mit dem Fahrzeug-Connect verbunden werden?</voice></speak>"
    )

    private var sample41: VoiceSample = VoiceSample(
        name = "G000962",
        description = "Dann wähle ich die Nummer mal.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Dann wähle ich die Nummer mal.</voice></speak>"
    )

    private var sample42: VoiceSample = VoiceSample(
        name = "G000963",
        description = "Eine Dashcam-Aufnahme ist zurzeit nicht möglich.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Eine Dashcam-Aufnahme ist zurzeit nicht möglich.</voice></speak>"
    )

    private var sample43: VoiceSample = VoiceSample(
        name = "G000986",
        description = "Die Fenster vorne links sind offen.",
        type = "General",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Die Fenster vorne links sind offen.</voice></speak>"
    )

    //Happy (Cheerful)
    private var sample44: VoiceSample = VoiceSample(
        name = "H000008",
        description = "Sorgen Sie für Abwechslung!",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Sorgen Sie für Abwechslung!</mstts:express-as></voice></speak>"
    )

    private var sample45: VoiceSample = VoiceSample(
        name = "H000033",
        description = "Muss ich dem überhaupt noch etwas hinzufügen?",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Muss ich dem überhaupt noch etwas hinzufügen?</mstts:express-as></voice></speak>"
    )

    private var sample46: VoiceSample = VoiceSample(
        name = "H000119",
        description = "Ich wünschte, ich wäre ein amphibisches Auto.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Ich wünschte, ich wäre ein amphibisches Auto.</mstts:express-as></voice></speak>"
    )

    private var sample47: VoiceSample = VoiceSample(
        name = "H000150",
        description = "Freundschaft ist ein Band, das selbst die weitesten Distanzen überbrücken kann.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Freundschaft ist ein Band, das selbst die weitesten Distanzen überbrücken kann.</mstts:express-as></voice></speak>"
    )

    private var sample48: VoiceSample = VoiceSample(
        name = "H000151",
        description = "Dann haben wir hier was für dich : Hier kannst du direkt reinhören!",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Dann haben wir hier was für dich : Hier kannst du direkt reinhören!</mstts:express-as></voice></speak>"
    )

    private var sample49: VoiceSample = VoiceSample(
        name = "H000188",
        description = "Jeder Schwung fühlt sich besser an als der vorherige.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Jeder Schwung fühlt sich besser an als der vorherige.</mstts:express-as></voice></speak>"
    )

    private var sample50: VoiceSample = VoiceSample(
        name = "H000235",
        description = "Nostalgische Erinnerungen aus der Kindheit zaubern ein Lächeln ins Gesicht, und sie erinnern uns an die Unbeschwertheit früherer Tage.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Nostalgische Erinnerungen aus der Kindheit zaubern ein Lächeln ins Gesicht, und sie erinnern uns an die Unbeschwertheit früherer Tage.</mstts:express-as></voice></speak>"
    )

    private var sample51: VoiceSample = VoiceSample(
        name = "H000326",
        description = "Haie gibt es seit über vier hundert Millionen Jahren, sie sind perfekte Jäger der Evolution.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Haie gibt es seit über vier hundert Millionen Jahren, sie sind perfekte Jäger der Evolution.</mstts:express-as></voice></speak>"
    )

    private var sample52: VoiceSample = VoiceSample(
        name = "H000360",
        description = "Doch wie sollte der Kleene heißen?",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Doch wie sollte der Kleene heißen?</mstts:express-as></voice></speak>"
    )

    private var sample53: VoiceSample = VoiceSample(
        name = "H000421",
        description = "Weitgehend dominiert Sonnenschein.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Weitgehend dominiert Sonnenschein.</mstts:express-as></voice></speak>"
    )

    private var sample54: VoiceSample = VoiceSample(
        name = "H000422",
        description = "Delfine schlafen mit nur einer Gehirnhälfte, die andere bleibt wach, um Gefahren zu erkennen.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Delfine schlafen mit nur einer Gehirnhälfte, die andere bleibt wach, um Gefahren zu erkennen.</mstts:express-as></voice></speak>"
    )

    private var sample55: VoiceSample = VoiceSample(
        name = "H000447",
        description = "Vielleicht können wir bald wieder eine machen?",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Vielleicht können wir bald wieder eine machen?</mstts:express-as></voice></speak>"
    )

    private var sample56: VoiceSample = VoiceSample(
        name = "H000483",
        description = "Hast du schon das neue Auto gesehen das ich mir gekauft habe?",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Hast du schon das neue Auto gesehen das ich mir gekauft habe?</mstts:express-as></voice></speak>"
    )

    private var sample57: VoiceSample = VoiceSample(
        name = "H000565",
        description = "Zu schön um wahr zu sein : Diese vier Frisuren - Trends im Sommer zwei tausend zwei und zwanzig verleihen langen Haaren ein modernes Makeover.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Zu schön um wahr zu sein : Diese vier Frisuren - Trends im Sommer zwei tausend zwei und zwanzig verleihen langen Haaren ein modernes Makeover.</mstts:express-as></voice></speak>"
    )

    private var sample58: VoiceSample = VoiceSample(
        name = "H000627",
        description = "Ich war ein fröhliches Kind, sehr extrovertiert.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Ich war ein fröhliches Kind, sehr extrovertiert.</mstts:express-as></voice></speak>"
    )

    private var sample59: VoiceSample = VoiceSample(
        name = "H000647",
        description = "Und dann, wenn das Spiel in die entscheidende Phase geht, merkst du, wie die Anspannung im Stadion fast unerträglich wird.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Und dann, wenn das Spiel in die entscheidende Phase geht, merkst du, wie die Anspannung im Stadion fast unerträglich wird.</mstts:express-as></voice></speak>"
    )

    private var sample60: VoiceSample = VoiceSample(
        name = "H000711",
        description = "Seesterne können ihre Arme nachwachsen lassen, wenn sie verletzt oder verloren gehen.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Seesterne können ihre Arme nachwachsen lassen, wenn sie verletzt oder verloren gehen.</mstts:express-as></voice></speak>"
    )

    private var sample61: VoiceSample = VoiceSample(
        name = "H000726",
        description = "Aber in unseren Köpfen war es ein unzerstörbarer Bunker.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Aber in unseren Köpfen war es ein unzerstörbarer Bunker.</mstts:express-as></voice></speak>"
    )

    private var sample62: VoiceSample = VoiceSample(
        name = "H000745",
        description = "Taille, Bauch, Rücken, Beckenboden, Po und Beine werden trainiert.",
        type = "Happy",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='cheerful' styledegree='2'>Taille, Bauch, Rücken, Beckenboden, Po und Beine werden trainiert.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm1: VoiceSample = VoiceSample(
        name = "SAR000005",
        description = "Aber Camilas Geschmack soll Leo nicht zugesagt haben.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Aber Camilas Geschmack soll Leo nicht zugesagt haben.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm2: VoiceSample = VoiceSample(
        name = "SAR000084",
        description = "Mein Computer ist natürlich schnell.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Mein Computer ist natürlich schnell.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm3: VoiceSample = VoiceSample(
        name = "SAR000144",
        description = "Ja, warum nicht durch die Mittagspause arbeiten, das macht doch jeder gerne!",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ja, warum nicht durch die Mittagspause arbeiten, das macht doch jeder gerne!</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm4: VoiceSample = VoiceSample(
        name = "SAR000227",
        description = "Es ist nur schwer übersehbar, dass alles auf eine Luftpolstergesellschaft hinauszulaufen scheint, in der die Eltern panisch bestrebt sind, von ihren Liebsten jedwede Form von Kränkung oder Verletzung fernzuhalten.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Es ist nur schwer übersehbar, dass alles auf eine Luftpolstergesellschaft hinauszulaufen scheint, in der die Eltern panisch bestrebt sind, von ihren Liebsten jedwede Form von Kränkung oder Verletzung fernzuhalten.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm5: VoiceSample = VoiceSample(
        name = "SAR000364",
        description = "Ja klar, weil es immer super läuft, wenn man die Ex mitten im Date erwähnt.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ja klar, weil es immer super läuft, wenn man die Ex mitten im Date erwähnt.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm6: VoiceSample = VoiceSample(
        name = "SAR000396",
        description = "Oh schau, noch ein Montag, an dem nichts nach Plan läuft – wie wunderbar!",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Oh schau, noch ein Montag, an dem nichts nach Plan läuft – wie wunderbar!</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm7: VoiceSample = VoiceSample(
        name = "SAR000414",
        description = "Ganz davon abgesehen - wer soll das bezahlen?",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ganz davon abgesehen - wer soll das bezahlen?</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm8: VoiceSample = VoiceSample(
        name = "SAR000441",
        description = "EZB-Präsidentin Christine Lagarde forderte heute alle Einwohner der europäischen Währungsunion dazu auf, ihre Geldscheine mit einem wasserfesten Stift um eine Null zu erweitern.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>EZB-Präsidentin Christine Lagarde forderte heute alle Einwohner der europäischen Währungsunion dazu auf, ihre Geldscheine mit einem wasserfesten Stift um eine Null zu erweitern.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm9: VoiceSample = VoiceSample(
        name = "SAR000476",
        description = "Sollen wir das jetzt die gesamte Woche ertragen?",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Sollen wir das jetzt die gesamte Woche ertragen?</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm10: VoiceSample = VoiceSample(
        name = "SAR000485",
        description = "Sicher, ich brauche unbedingt noch eine Erinnerung daran, dass ich unsportlich bin!",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Sicher, ich brauche unbedingt noch eine Erinnerung daran, dass ich unsportlich bin!</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm11: VoiceSample = VoiceSample(
        name = "SAR000634",
        description = "Ach ja, nichts sagt \"Karrierechancen\" so wie der hundertste unerledigte Stapel auf meinem Schreibtisch.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ach ja, nichts sagt \"Karrierechancen\" so wie der hundertste unerledigte Stapel auf meinem Schreibtisch.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm12: VoiceSample = VoiceSample(
        name = "SAR000638",
        description = "Ich habe bereits alles versucht, was in meiner Macht steht.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ich habe bereits alles versucht, was in meiner Macht steht.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm13: VoiceSample = VoiceSample(
        name = "SAR000650",
        description = "Bitte mach weiter so.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Bitte mach weiter so.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm14: VoiceSample = VoiceSample(
        name = "SAR000663",
        description = "Ich fühle mich so schuldig, und es frisst mich innerlich auf.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ich fühle mich so schuldig, und es frisst mich innerlich auf.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm15: VoiceSample = VoiceSample(
        name = "SAR000664",
        description = "Geladen waren zahlreiche prominente Gäste aus Politik und Gesellschaft.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Geladen waren zahlreiche prominente Gäste aus Politik und Gesellschaft.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm16: VoiceSample = VoiceSample(
        name = "SAR000668",
        description = "Ich liebe es, immer pünktlich zu sein.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Ich liebe es, immer pünktlich zu sein.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm17: VoiceSample = VoiceSample(
        name = "SAR000685",
        description = "Wie produktiv, eine virtuelle Farm zu bewirtschaften, statt echtes Gemüse anzubauen – das ist wirklich eine sinnvolle Nutzung deiner Zeit.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Wie produktiv, eine virtuelle Farm zu bewirtschaften, statt echtes Gemüse anzubauen – das ist wirklich eine sinnvolle Nutzung deiner Zeit.</mstts:express-as></voice></speak>"
    )

    private var sampleSarcasm18: VoiceSample = VoiceSample(
        name = "SAR000830",
        description = "Vielleicht entwickelst du in ein paar Millionen Jahren ja tatsächlich noch einen Sinn für Humor, auch wenn die Chancen recht gering erscheinen.",
        type = "Sarcasm",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='sarcastic' styledegree='2'>Vielleicht entwickelst du in ein paar Millionen Jahren ja tatsächlich noch einen Sinn für Humor, auch wenn die Chancen recht gering erscheinen.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious1: VoiceSample = VoiceSample(
        name = "SE000002",
        description = "Es muss mit höchster Genauigkeit durchgeführt werden!",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Es muss mit höchster Genauigkeit durchgeführt werden!</mstts:express-as></voice></speak>"
    )

    private var sampleSerious2: VoiceSample = VoiceSample(
        name = "SE000017",
        description = "Die Straßensperrungen im nächsten Abschnitt werden zu einer Umleitung führen.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Die Straßensperrungen im nächsten Abschnitt werden zu einer Umleitung führen.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious3: VoiceSample = VoiceSample(
        name = "SE000024",
        description = "Aufgrund der Wetterlage sollten Sie zusätzliche Zeit für eventuelle Verzögerungen einplanen.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Aufgrund der Wetterlage sollten Sie zusätzliche Zeit für eventuelle Verzögerungen einplanen.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious4: VoiceSample = VoiceSample(
        name = "SE000068",
        description = "Warum sollen wir wählen, wenn es am Ende weitergeht wie vorher?",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Warum sollen wir wählen, wenn es am Ende weitergeht wie vorher?</mstts:express-as></voice></speak>"
    )

    private var sampleSerious5: VoiceSample = VoiceSample(
        name = "SE000080",
        description = "Sofort handeln!",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Sofort handeln!</mstts:express-as></voice></speak>"
    )

    private var sampleSerious6: VoiceSample = VoiceSample(
        name = "SE000168",
        description = "Tesla ließ eine Anfrage von Stern und RTL unbeantwortet, die Staatskanzlei Brandenburg verwies auf das Umweltministerium.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Tesla ließ eine Anfrage von Stern und RTL unbeantwortet, die Staatskanzlei Brandenburg verwies auf das Umweltministerium.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious7: VoiceSample = VoiceSample(
        name = "SE000213",
        description = "Warum hast du nicht rechtzeitig auf die Warnzeichen reagiert?",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Warum hast du nicht rechtzeitig auf die Warnzeichen reagiert?</mstts:express-as></voice></speak>"
    )

    private var sampleSerious8: VoiceSample = VoiceSample(
        name = "SE000270",
        description = "Sie wollen etwas gegen den Hunger in der Welt tun?",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Sie wollen etwas gegen den Hunger in der Welt tun?</mstts:express-as></voice></speak>"
    )

    private var sampleSerious9: VoiceSample = VoiceSample(
        name = "SE000331",
        description = "Im internationalen Wettbewerb können wir nur bestehen, wenn wir wichtige europäische Industrieprojekte mit hohem Einsatz umsetzen.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Im internationalen Wettbewerb können wir nur bestehen, wenn wir wichtige europäische Industrieprojekte mit hohem Einsatz umsetzen.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious10: VoiceSample = VoiceSample(
        name = "SE000339",
        description = "Ein niedriger Reifendruck kann zu gefährlichen Situationen auf der Autobahn führen.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Ein niedriger Reifendruck kann zu gefährlichen Situationen auf der Autobahn führen.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious11: VoiceSample = VoiceSample(
        name = "SE000371",
        description = "Politisch sei mit dem Krieg die Voraussetzung für eine Notlage gegeben, die eine erweiterte Kreditaufnahme ermögliche.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Politisch sei mit dem Krieg die Voraussetzung für eine Notlage gegeben, die eine erweiterte Kreditaufnahme ermögliche.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious12: VoiceSample = VoiceSample(
        name = "SE000400",
        description = "Das meinten Sie doch!",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Das meinten Sie doch!</mstts:express-as></voice></speak>"
    )

    private var sampleSerious13: VoiceSample = VoiceSample(
        name = "SE000653",
        description = "Man hat den Eindruck, das Wichtigste ist Ihnen, die Leute wieder loszuwerden.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Man hat den Eindruck, das Wichtigste ist Ihnen, die Leute wieder loszuwerden.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious14: VoiceSample = VoiceSample(
        name = "SE000785",
        description = "Der menschliche Schlafzyklus besteht aus mehreren Phasen, darunter der REM-Schlaf, in dem Träume am häufigsten auftreten und die Gehirnaktivität erhöht ist.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Der menschliche Schlafzyklus besteht aus mehreren Phasen, darunter der REM-Schlaf, in dem Träume am häufigsten auftreten und die Gehirnaktivität erhöht ist.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious15: VoiceSample = VoiceSample(
        name = "SE000797",
        description = "Die kontinuierliche Erosion von Wasser und Wind formt die Landschaften der Erde über Millionen von Jahren hinweg.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Die kontinuierliche Erosion von Wasser und Wind formt die Landschaften der Erde über Millionen von Jahren hinweg.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious16: VoiceSample = VoiceSample(
        name = "SE000946",
        description = "Die Beleuchtung des angeschlossenen Anhängers ist funktionsfähig.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>Die Beleuchtung des angeschlossenen Anhängers ist funktionsfähig.</mstts:express-as></voice></speak>"
    )

    private var sampleSerious17: VoiceSample = VoiceSample(
        name = "SE000961",
        description = "In dreihundert Metern gibt es einen Rastplatz.",
        type = "Serious",
        ssml = "<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'><mstts:express-as style='serious' styledegree='2'>In dreihundert Metern gibt es einen Rastplatz.</mstts:express-as></voice></speak>"
    )

    private var EmpathicSamples: List<VoiceSample> = listOf(
        sample2,
        sample3,
        sample4,
        sample5,
        sample6,
        sample7,
        sample8,
        sample9,
        sample10,
        sample11,
        sample12,
        sample13,
        sample14,
        sample15,
        sample16,
        sample17,
        sample18,
        sample19,
        sample20,
        sample21,
        sample22,
        sample23
    )
    private var GeneralSamples: List<VoiceSample> = listOf(
        sample24,
        sample25,
        sample26,
        sample27,
        sample28,
        sample29,
        sample30,
        sample31,
        sample32,
        sample33,
        sample34,
        sample35,
        sample36,
        sample37,
        sample38,
        sample39,
        sample40,
        sample41,
        sample42,
        sample43
    )
    private var HappySamples: List<VoiceSample> = listOf(
        sample44,
        sample45,
        sample46,
        sample47,
        sample48,
        sample49,
        sample50,
        sample51,
        sample52,
        sample53,
        sample54,
        sample55,
        sample56,
        sample57,
        sample58,
        sample59,
        sample60,
        sample61,
        sample62
    )
    private var SarcasticSamples: List<VoiceSample> = listOf(
        sampleSarcasm1,
        sampleSarcasm2,
        sampleSarcasm3,
        sampleSarcasm4,
        sampleSarcasm5,
        sampleSarcasm6,
        sampleSarcasm7,
        sampleSarcasm8,
        sampleSarcasm9,
        sampleSarcasm10,
        sampleSarcasm11,
        sampleSarcasm12,
        sampleSarcasm13,
        sampleSarcasm14,
        sampleSarcasm15,
        sampleSarcasm16,
        sampleSarcasm17,
        sampleSarcasm18
    )
    private var SeriousSamples: List<VoiceSample> = listOf(
        sampleSerious1,
        sampleSerious2,
        sampleSerious3,
        sampleSerious4,
        sampleSerious5,
        sampleSerious6,
        sampleSerious7,
        sampleSerious8,
        sampleSerious9,
        sampleSerious10,
        sampleSerious11,
        sampleSerious12,
        sampleSerious13,
        sampleSerious14,
        sampleSerious15,
        sampleSerious16,
        sampleSerious17
    )

    public var Samples: MutableList<VoiceSample> = mutableListOf()

    constructor() {
        Samples.addAll(EmpathicSamples)
        Samples.addAll(GeneralSamples)
        Samples.addAll(HappySamples)
        Samples.addAll(SarcasticSamples)
        Samples.addAll(SeriousSamples)
    }
}