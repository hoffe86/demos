package com.example.ttsclient

import java.util.Locale
import java.util.UUID

enum class PhraseType {
    PLAIN_TEXT,
    SSML
}

data class PhraseItem(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val type: PhraseType,
    val language: Locale,
    var startTime: Long? = null,
    var endTime: Long? = null,
    var durationMs: Double? = null,
    var status: String = "Pending"
)

// ATTENTION: This object to hold all predefined phrase data
object PhraseData {

    // Map keys are now String (language code) instead of Locale
    val ALL_PHRASES: Map<String, Map<PhraseType, List<PhraseItem>>> = mapOf(
        Locale.ENGLISH.language to mapOf( // Use Locale.ENGLISH.language ("en") as key
            PhraseType.PLAIN_TEXT to listOf(
// <100; ambient_light_color_yellow__status_ok_positive_VP_CONCEPT
                PhraseItem(text = "Yellow. Such a warm feeling.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; info_ok_retrieving_data_VP_CONCEPT
                PhraseItem(text = "OK, I'll have a look.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; navi_ask_for_state_VP_CONCEPT
                PhraseItem(text = "What is the name of the state?", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; ambient_light_color_cyan__status_ok_VP_CONCEPT
                PhraseItem(text = "OK, I'm changing the colour.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; adb_call_contact_private_CONCEPT
                PhraseItem(text = "OK, I'm calling the private number.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; info_navi_guidance_aborted_VP_CONCEPT
                PhraseItem(text = "I am cancelling route guidance now.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; interior_light__not_avail_for_front_CONCEPT
                PhraseItem(text = "In the front, there is no interior lighting.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; info_vehiclefunctions_ev_charging_flap_already_open_CONCEPT
                PhraseItem(text = "The charging flap is already open.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; calf_massage_front_seat_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "I'm switching on the massage programme in the front.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; temperature_rear_warmer__status_ok_VP_CONCEPT
                PhraseItem(text = "OK, I'll increase the temperature in the rear.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; msg_info_no_inbox_emails_CONCEPT
                PhraseItem(text = "I couldn't find any e-mails in your inbox.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; reading_light_front_side_on__status_ok_VP_CONCEPT
                PhraseItem(text = "Activating the front reading lights.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; sound_experience_already_off_VP_CONCEPT
                PhraseItem(text = "The Sound Experience is already deactivated.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; vehiclefunctions_trailer_hitch_already_extended_VP_CONCEPT
                PhraseItem(text = "The trailer coupling is already swung out.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; vehiclefunctions_info_dashcam_recording_not_possible_VP_CONCEPT
                PhraseItem(text = "It is currently not possible to make a dashcam recording.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; front_passenger_energizing_seat_kinetics_on__status_ok_VP_CONCEPT
                PhraseItem(text = "OK, I'm turning on seat kinetics for the front passenger.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; info_navi_switch_to_minimalist_ADR_VP_CONCEPT
                PhraseItem(text = "I have activated the extra-concise driving announcements.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; magic_sky_control__status_nok_VP_CONCEPT
                PhraseItem(text = "I cannot operate the Magic Sky Control function at the moment.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; player_multiseat_playback_start_at_front_left_seat_confirmation_CONCEPT
                PhraseItem(text = "OK, playback has started on the front left seat.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; summer_rain_on_front__status_ok_VP_CONCEPT
                PhraseItem(text = "I am activating Summer Rain for the front seats. It's so soothing!", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; windows_open_change_singular_half_all_CONCEPT
                PhraseItem(text = "Understood, I'm opening the window halfway.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; info_navi_satellite_map_on_VP_CONCEPT
                PhraseItem(text = "OK! The satellite map is showing now.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; media_ask_play_composer_or_artist_CONCEPT
                PhraseItem(text = "Which would you rather listen to - the composer or the artist?", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; parking_sound_deactivated_ok_VP_CONCEPT
                PhraseItem(text = "The acoustic parking aid is now deactivated.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// <100; vehiclefunctions_turn_vehicle_on_VP_CONCEPT
                PhraseItem(text = "Please start the vehicle to use the function.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; massage_program_classic_front_right_on_status_ok_CONCEPT
                PhraseItem(text = "OK, I'm activating the massage on the front right seat. Enjoy it.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; front_passenger_mobilizing_massage_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "I am switching on Mobilising Massage for the front passenger seat.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; ask_global_pardon_1_VP_CONCEPT
                PhraseItem(text = "Could you repeat that please?", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; autonomous_driving_driver_camera_off_VP_CONCEPT
                PhraseItem(text = "I can only operate Drive Pilot when the driver camera is on.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; info_navi_destination_replaced_no_address_slots_VP_CONCEPT
                PhraseItem(text = "The selected destination is being replaced. I am updating our route.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; info_navi_auto_zoom_not_controllable_on_display_from_seat_VP_CONCEPT
                PhraseItem(text = "The auto zoom cannot be operated on this screen from your seat using voice control.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; info_vehicle_charging_ev_ECO_hybrid_na_QUICK_already_off_VP_CONCEPT
                PhraseItem(text = "ECO charging is not available in your car. The rapid-charge option is already deactivated and your car is being charged in a way that is easy on the battery.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; massage_program_classic_status_nok_implicit_CONCEPT
                PhraseItem(text = "I can't start the Classic Massage at the moment. However, please try straightening your spine and relaxing your shoulders. That will definitely help!", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; scent_already_on_level__status_ok_VP_CONCEPT
                PhraseItem(text = "Fragrancing is already set to level 3.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; msg_info_no_new_emails_from_contact_combination_CONCEPT
                PhraseItem(text = "No new e-mails from James Bond.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; info_navi_arrival_time_next_day_without_stopover_VP_CONCEPT
                PhraseItem(text = "You are expected to reach Berlin tomorrow at 14:00 o'clock.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// 100<x<400; range_assist_battery_sufficiently_charged_location_CONCEPT
                PhraseItem(text = "Your battery currently has a sufficient state of charge to reach Berlin. You should arrive with 45% battery charge.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; calendar_read_out_first_appointment_VP_CONCEPT
                PhraseItem(text = "The first appointment dentist appointment will last from 10:00 o'clock to 11:30 o'clock.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "Did you know that it could rain diamonds on Jupiter? Scientists believe that the extreme pressure and temperature conditions in the upper atmospheric layers of Jupiter could transform carbon into diamonds. These diamonds could then fall through the atmosphere like rain! Imagine diamonds falling from the sky – that's truly galactic luxury!", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; info_vehiclefunctions_ev_departure_time_deleted_tomorrow_no_access_VP_CONCEPT
                PhraseItem(text = "But so far in advance, I can only set repeated departure times. Therefore, I am preparing your car for departure every Monday at 08:00 o'clock.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "A black hole is like a giant vacuum cleaner in space. It has a super strong gravitational pull that attracts everything nearby, even light! Imagine you have a ball that you throw, and it always falls back to the ground. With a black hole, the ball would never come back because the gravitational pull is so strong that it simply disappears. So, it's a place in space where things vanish and can't come out again.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; range_assist_battery_not_sufficient_calculated_route_one_charging_stop_no_address_slots_VP_CONCEPT
                PhraseItem(text = "The battery is not charged enough to reach our destination. I have for this reason planned a route with one charging stop. We must charge the vehicle for 30 minutes and can expect to reach the destination with a residual charge of 21%.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; info_navi_remaining_time_traffic_delay_without_stopover_VP_CONCEPT
                PhraseItem(text = "We won't reach Berlin for about one hour. Traffic along the route is causing a delay of about five minutes.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; navi_traffic_information_OffRoute_traffic_delay_VP_CONCEPT
                PhraseItem(text = "Expect delays of five minutes on the fastest route. Plan around 30 minutes to get to Berlin.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "Mercedes-Benz is known for its innovative strength and regularly sets new standards in the automotive industry. The company continuously invests in research and development to create advanced technologies. These include electric drives, autonomous driving, and intelligent safety systems. Mercedes-Benz integrates the latest digital solutions and connected services into its vehicles to enhance the driving experience. By combining traditional engineering excellence with forward-thinking technology, Mercedes-Benz remains a leading innovator and shapes the mobility of tomorrow.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; info_PILOT_conditions_VP_CONCEPT
                PhraseItem(text = "The Navigation Guided Assist function assists you at speeds from 0 to 130 km/h on closed motorways or urban motorways and at 0 to 80 km/h on some urban roads. However, the assistance function is not available in areas such as toll stations, service areas, construction sites or static obstacles that the system cannot detect, as well as in bad weather or in complex scenarios. For more information, including on activation, usage conditions and warnings, please refer to the Owner's Manual. Navigation Guided Assist is not an autonomous driving feature, so please always pay attention to the traffic situation and make sure that you are driving safely.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "The German political system is a parliamentary democracy and consists of several important parts. The Bundestag is the central parliament where the representatives pass laws and control the government. The Bundesrat represents the interests of the federal states and participates in legislation. The Federal President is the head of state and primarily has representative duties. The Federal Government consists of the Chancellor and the Federal Ministers, who form the executive branch and implement policies. The Federal Constitutional Court monitors compliance with the Basic Law and protects citizens' rights. These institutions work together to ensure the democratic order and functioning of the state.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; info_navi_arrival_time_with_stopovers_tomorrow_and_date_VP_CONCEPT
                PhraseItem(text = "We will arrive at your next destination Hamburg tomorrow at 23:00 o'clock and your final destination Stuttgart on 17. may at 05:00 o'clock.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; main_help_overview_DEU_ENG_CONCEPT
                PhraseItem(text = "I can do things for you whenever you want, such as calling someone, writing text messages or playing music. I can also answer your questions, for example, about the football results or the weather forecast. Because my heart's in it, I know everything there is to know about driving and your vehicle. For example, you can ask me, \"Hey Mercedes, what kind of safety assistance systems are available\", \"Hey Mercedes, drive me home\", or, \"Hey Mercedes, is there congestion on my route\"? You can also tell me if you're too warm, too cold, or if you're bored or hungry. My goal is to constantly evolve in order to give you the best possible support. Every now and again, just ask me if I've learnt anything new. I'm looking forward to hearing from you and wish you a pleasant journey as always.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH),
// >400; LLM content
            PhraseItem(text = "Of course! Here is the guide to caring for a lemon tree. Choose a sunny spot, as lemon trees need a lot of light. Ideally, they should get at least 8 hours of direct sunlight per day. Use well-draining soil. A mix of garden soil and compost is suitable. Avoid heavy, clay-rich soils. Keep the soil evenly moist but not wet. Water regularly, especially during dry periods. Avoid waterlogging. Fertilize the tree during the growing season (spring and summer) about every 4-6 weeks with a special citrus fertilizer. Prune the tree in late winter or early spring to remove dead or diseased branches and maintain its shape. Regularly check for pests like aphids and spider mites. Treat infestations with an appropriate remedy. If you live in a region with cold winters, protect the tree from frost. Bring it indoors or into a greenhouse if temperatures drop below 5 degrees Celsius. With these tips, your lemon tree should thrive and provide you with many delicious fruits.", type = PhraseType.PLAIN_TEXT, language = Locale.ENGLISH)
            ),
            PhraseType.SSML to listOf(
// <100; ambient_light_color_yellow__status_ok_positive_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Yellow. Such a warm feeling.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; info_ok_retrieving_data_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'll have a look.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; navi_ask_for_state_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>What is the name of the state?</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; ambient_light_color_cyan__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'm changing the colour.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; adb_call_contact_private_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'm calling the private number.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; info_navi_guidance_aborted_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I am cancelling route guidance now.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; interior_light__not_avail_for_front_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>In the front, there is no interior lighting.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; info_vehiclefunctions_ev_charging_flap_already_open_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The charging flap is already open.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; calf_massage_front_seat_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I'm switching on the massage programme in the front.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; temperature_rear_warmer__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'll increase the temperature in the rear.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; msg_info_no_inbox_emails_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I couldn't find any e-mails in your inbox.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; reading_light_front_side_on__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Activating the front reading lights.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; sound_experience_already_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The Sound Experience is already deactivated.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; vehiclefunctions_trailer_hitch_already_extended_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The trailer coupling is already swung out.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; vehiclefunctions_info_dashcam_recording_not_possible_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>It is currently not possible to make a dashcam recording.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; front_passenger_energizing_seat_kinetics_on__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'm turning on seat kinetics for the front passenger.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; info_navi_switch_to_minimalist_ADR_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I have activated the extra-concise driving announcements.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; magic_sky_control__status_nok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I cannot operate the Magic Sky Control function at the moment.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; player_multiseat_playback_start_at_front_left_seat_confirmation_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, playback has started on the front left seat.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; summer_rain_on_front__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I am activating Summer Rain for the front seats. It's so soothing!</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; windows_open_change_singular_half_all_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Understood, I'm opening the window halfway.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; info_navi_satellite_map_on_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK! The satellite map is showing now.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; media_ask_play_composer_or_artist_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Which would you rather listen to - the composer or the artist?</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; parking_sound_deactivated_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The acoustic parking aid is now deactivated.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// <100; vehiclefunctions_turn_vehicle_on_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Please start the vehicle to use the function.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; massage_program_classic_front_right_on_status_ok_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>OK, I'm activating the massage on the front right seat. Enjoy it.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; front_passenger_mobilizing_massage_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I am switching on Mobilising Massage for the front passenger seat.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; ask_global_pardon_1_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Could you repeat that please?</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; autonomous_driving_driver_camera_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I can only operate Drive Pilot when the driver camera is on.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; info_navi_destination_replaced_no_address_slots_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The selected destination is being replaced. I am updating our route.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; info_navi_auto_zoom_not_controllable_on_display_from_seat_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The auto zoom cannot be operated on this screen from your seat using voice control.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; info_vehicle_charging_ev_ECO_hybrid_na_QUICK_already_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>ECO charging is not available in your car. The rapid-charge option is already deactivated and your car is being charged in a way that is easy on the battery.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; massage_program_classic_status_nok_implicit_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I can't start the Classic Massage at the moment. However, please try straightening your spine and relaxing your shoulders. That will definitely help!</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; scent_already_on_level__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Fragrancing is already set to level <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_parameter\">3</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; msg_info_no_new_emails_from_contact_combination_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>No new e-mails from <say-as interpret-as=\"TTS-PROPERNAME\"><say-as format=\"slot\" interpret-as=\"g_contact_combination\">James Bond</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; info_navi_arrival_time_next_day_without_stopover_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>You are expected to reach <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as> tomorrow at <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_final_destination_arrival_time\">14:00 o'clock</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// 100<x<400; range_assist_battery_sufficiently_charged_location_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Your battery currently has a sufficient state of charge to reach <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as>. You should arrive with <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_loading_status\">45</say-as></say-as> % battery charge.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; calendar_read_out_first_appointment_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The first appointment <say-as interpret-as=\"UNSPECIFIED\"><say-as format=\"slot\" interpret-as=\"g_title\">dentist appointment</say-as></say-as> will last from <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_starttime\">10:00 o'clock</say-as></say-as> to <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_endtime\">11:30 o'clock</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Did you know that it could rain diamonds on Jupiter? Scientists believe that the extreme pressure and temperature conditions in the upper atmospheric layers of Jupiter could transform carbon into diamonds. These diamonds could then fall through the atmosphere like rain! Imagine diamonds falling from the sky – that's truly galactic luxury!</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; info_vehiclefunctions_ev_departure_time_deleted_tomorrow_no_access_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>But so far in advance, I can only set repeated departure times. Therefore, I am preparing your car for departure every <say-as interpret-as=\"UNSPECIFIED\"><say-as format=\"slot\" interpret-as=\"g_weekday\">Monday</say-as></say-as> at <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_ev_departure_time\">08:00 o'clock</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>A black hole is like a giant vacuum cleaner in space. It has a super strong gravitational pull that attracts everything nearby, even light! Imagine you have a ball that you throw, and it always falls back to the ground. With a black hole, the ball would never come back because the gravitational pull is so strong that it simply disappears. So, it's a place in space where things vanish and can't come out again.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; range_assist_battery_not_sufficient_calculated_route_one_charging_stop_no_address_slots_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The battery is not charged enough to reach our destination. I have for this reason planned a route with one charging stop. We must charge the vehicle for <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_charging_time\">30 minutes</say-as></say-as> and can expect to reach the destination with a residual charge of <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_loading_status\">21</say-as></say-as>%.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; info_navi_remaining_time_traffic_delay_without_stopover_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>We won't reach <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as> for about <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_final_destination_remaining_time\">one hour</say-as></say-as>. Traffic along the route is causing a delay of about <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"traffic_delay_time\">five minutes</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; navi_traffic_information_OffRoute_traffic_delay_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Expect delays of <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_requested_destination_traffic_delay_time\">five minutes</say-as></say-as> on the fastest route. Plan around <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_requested_destination_driving_time\">30 minutes</say-as></say-as> to get to <say-as format=\"slot\" interpret-as=\"g_final_destination\">Berlin</say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Mercedes-Benz is known for its innovative strength and regularly sets new standards in the automotive industry. The company continuously invests in research and development to create advanced technologies. These include electric drives, autonomous driving, and intelligent safety systems. Mercedes-Benz integrates the latest digital solutions and connected services into its vehicles to enhance the driving experience. By combining traditional engineering excellence with forward-thinking technology, Mercedes-Benz remains a leading innovator and shapes the mobility of tomorrow.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; info_PILOT_conditions_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The Navigation Guided Assist function assists you at speeds from 0 to 130 km/h on closed motorways or urban motorways and at 0 to 80 km/h on some urban roads. However, the assistance function is not available in areas such as toll stations, service areas, construction sites or static obstacles that the system cannot detect, as well as in bad weather or in complex scenarios. For more information, including on activation, usage conditions and warnings, please refer to the Owner's Manual. Navigation Guided Assist is not an autonomous driving feature, so please always pay attention to the traffic situation and make sure that you are driving safely.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>The German political system is a parliamentary democracy and consists of several important parts. The Bundestag is the central parliament where the representatives pass laws and control the government. The Bundesrat represents the interests of the federal states and participates in legislation. The Federal President is the head of state and primarily has representative duties. The Federal Government consists of the Chancellor and the Federal Ministers, who form the executive branch and implement policies. The Federal Constitutional Court monitors compliance with the Basic Law and protects citizens' rights. These institutions work together to ensure the democratic order and functioning of the state.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; info_navi_arrival_time_with_stopovers_tomorrow_and_date_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>We will arrive at your next destination <say-as format=\"slot\" interpret-as=\"g_first_intermediate_destination_info\">Hamburg</say-as> tomorrow at <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_first_destination_arrival_time\">23:00 o'clock</say-as></say-as> and your final destination <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Stuttgart</say-as> on <say-as interpret-as=\"TTS-DATE\"><say-as format=\"slot\" interpret-as=\"g_final_destination_date\">17. may</say-as></say-as> at <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_final_destination_arrival_time\">05:00 o'clock</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; main_help_overview_DEU_ENG_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>I can do things for you whenever you want, such as calling someone, writing text messages or playing music. I can also answer your questions, for example, about the football results or the weather forecast. Because my heart's in it, I know everything there is to know about driving and your vehicle. For example, you can ask me, \"Hey Mercedes, what kind of safety assistance systems are available\", \"Hey Mercedes, drive me home\", or, \"Hey Mercedes, is there congestion on my route\"? You can also tell me if you're too warm, too cold, or if you're bored or hungry. My goal is to constantly evolve in order to give you the best possible support. Every now and again, just ask me if I've learnt anything new. I'm looking forward to hearing from you and wish you a pleasant journey as always.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH),
// >400; LLM content
            PhraseItem(text = "<?xml version=\"1.0\"?><speak>Of course! Here is the guide to caring for a lemon tree. Choose a sunny spot, as lemon trees need a lot of light. Ideally, they should get at least 8 hours of direct sunlight per day. Use well-draining soil. A mix of garden soil and compost is suitable. Avoid heavy, clay-rich soils. Keep the soil evenly moist but not wet. Water regularly, especially during dry periods. Avoid waterlogging. Fertilize the tree during the growing season (spring and summer) about every 4-6 weeks with a special citrus fertilizer. Prune the tree in late winter or early spring to remove dead or diseased branches and maintain its shape. Regularly check for pests like aphids and spider mites. Treat infestations with an appropriate remedy. If you live in a region with cold winters, protect the tree from frost. Bring it indoors or into a greenhouse if temperatures drop below 5 degrees Celsius. With these tips, your lemon tree should thrive and provide you with many delicious fruits.</speak>", type = PhraseType.SSML, language = Locale.ENGLISH)
            )
        ),
        Locale.GERMAN.language to mapOf( // Use Locale.GERMAN.language ("de") as key
            PhraseType.PLAIN_TEXT to listOf(
// <100; ambient_light_color_yellow__status_ok_positive_VP_CONCEPT
                PhraseItem(text = "Gelb. So schön warm.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; info_ok_retrieving_data_VP_CONCEPT
                PhraseItem(text = "Ok, ich schaue nach.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; navi_ask_for_state_VP_CONCEPT
                PhraseItem(text = "Wie heißt der Staat?", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; ambient_light_color_cyan__status_ok_VP_CONCEPT
                PhraseItem(text = "Ich ändere die Farbe.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; adb_call_contact_private_CONCEPT
                PhraseItem(text = "Ich wähle die private Nummer.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; info_navi_guidance_aborted_VP_CONCEPT
                PhraseItem(text = "Ich breche die Zielführung ab.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; interior_light__not_avail_for_front_CONCEPT
                PhraseItem(text = "Es gibt vorne kein Innenlicht.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; info_vehiclefunctions_ev_charging_flap_already_open_CONCEPT
                PhraseItem(text = "Die Ladeklappe ist schon offen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; calf_massage_front_seat_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "Ich aktiviere vorne die Massage.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; temperature_rear_warmer__status_ok_VP_CONCEPT
                PhraseItem(text = "Ich erhöhe die Temperatur hinten.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; msg_info_no_inbox_emails_CONCEPT
                PhraseItem(text = "Ich konnte keine E-Mails im Posteingang finden.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; reading_light_front_side_on__status_ok_VP_CONCEPT
                PhraseItem(text = "Verstanden, Leselicht vorne wird eingeschaltet.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; sound_experience_already_off_VP_CONCEPT
                PhraseItem(text = "Die Sound Experience ist bereits ausgeschaltet.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; vehiclefunctions_trailer_hitch_already_extended_VP_CONCEPT
                PhraseItem(text = "Die Anhängerkupplung ist bereits ausgeschwenkt.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; vehiclefunctions_info_dashcam_recording_not_possible_VP_CONCEPT
                PhraseItem(text = "Eine Dashcam-Aufnahme ist zurzeit nicht möglich.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; front_passenger_energizing_seat_kinetics_on__status_ok_VP_CONCEPT
                PhraseItem(text = "Ich starte die Sitzkinetik für den Beifahrersitz.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; info_navi_switch_to_minimalist_ADR_VP_CONCEPT
                PhraseItem(text = "Ich habe die extra kurzen Fahrhinweise aktiviert.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; magic_sky_control__status_nok_VP_CONCEPT
                PhraseItem(text = "Ich kann Magic Sky Control gerade nicht bedienen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; player_multiseat_playback_start_at_front_left_seat_confirmation_CONCEPT
                PhraseItem(text = "Okay, die Wiedergabe vorne links wurde gestartet.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; summer_rain_on_front__status_ok_VP_CONCEPT
                PhraseItem(text = "Ich schalte vorne Sommerregen ein. Ruht euch aus!", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; windows_open_change_singular_half_all_CONCEPT
                PhraseItem(text = "Verstanden, das Fenster wird zur Hälfte geöffnet.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; info_navi_satellite_map_on_VP_CONCEPT
                PhraseItem(text = "Alles klar, die Satellitenkarte wird eingeblendet.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; media_ask_play_composer_or_artist_CONCEPT
                PhraseItem(text = "Willst du den Komponisten oder den Künstler hören?", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; parking_sound_deactivated_ok_VP_CONCEPT
                PhraseItem(text = "Die akustische Einparkhilfe ist jetzt deaktiviert.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// <100; vehiclefunctions_turn_vehicle_on_VP_CONCEPT
                PhraseItem(text = "Bitte mache das Fahrzeug an, um die Funktion bedienen zu können.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; massage_program_classic_front_right_on_status_ok_CONCEPT
                PhraseItem(text = "Klassische Massage beginnt jetzt vorne rechts. Genieß' die Fahrt.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; front_passenger_mobilizing_massage_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "Gern. Entspannende Massage für den Beifahrersitz ist jetzt aktiv.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; ask_global_pardon_1_VP_CONCEPT
                PhraseItem(text = "Kannst du das bitte wiederholen? Ich hab’s nicht ganz mitbekommen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; autonomous_driving_driver_camera_off_VP_CONCEPT
                PhraseItem(text = "Ich kann den Drive Pilot nur bei aktivierter Fahrerkamera bedienen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; info_navi_destination_replaced_no_address_slots_VP_CONCEPT
                PhraseItem(text = "Ich werde das genannte Ziel austauschen und berechne die Route neu.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; info_navi_auto_zoom_not_controllable_on_display_from_seat_VP_CONCEPT
                PhraseItem(text = "Du kannst den Autozoom auf dem genannten Bildschirm von deinem Sitz aus nicht per Sprache bedienen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; info_vehicle_charging_ev_ECO_hybrid_na_QUICK_already_off_VP_CONCEPT
                PhraseItem(text = "ECO-Laden ist in deinem Auto nicht verfügbar. Die Schnelllade-Option ist schon ausgeschaltet. Damit wird dein Auto batterieschonend geladen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; massage_program_classic_status_nok_implicit_CONCEPT
                PhraseItem(text = "Leider kann ich die Klassische Massage gerade nicht starten, aber versuch doch mal deine Wirbelsäule ganz aufzurichten und deine Schultern zu entspannen. Das hilft!", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; scent_already_on_level__status_ok_VP_CONCEPT
                PhraseItem(text = "Die Beduftung ist bereits auf Stufe 3 eingestellt.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; msg_info_no_new_emails_from_contact_combination_CONCEPT
                PhraseItem(text = "Ich habe keine neuen E-Mails von Max Mustermann gefunden.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; info_navi_arrival_time_next_day_without_stopover_VP_CONCEPT
                PhraseItem(text = "Voraussichtlich erreichst Du Berlin morgen um 14:00 Uhr.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// 100<x<400; range_assist_battery_sufficiently_charged_location_CONCEPT
                PhraseItem(text = "Deine Batterie ist momentan ausreichend geladen, um Berlin zu erreichen. Du würdest mit voraussichtlich 45% Batterie ankommen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; calendar_read_out_first_appointment_VP_CONCEPT
                PhraseItem(text = "Der erste Termin Zahnarzttermin geht von 10:00 Uhr bis 11:30 Uhr.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "Wusstest du, dass es auf dem Jupiter Diamanten regnen könnte? Wissenschaftler glauben, dass die extremen Druck- und Temperaturbedingungen in den oberen Atmosphärenschichten des Jupiter Kohlenstoff in Diamanten verwandeln könnten. Diese Diamanten könnten dann durch die Atmosphäre fallen, wie Regen! Stell dir vor, Diamanten, die vom Himmel fallen – das ist wirklich galaktischer Luxus!", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; info_vehiclefunctions_ev_departure_time_deleted_tomorrow_no_access_VP_CONCEPT
                PhraseItem(text = "Aber so weit im Voraus kann ich nur Abfahrtszeiten einstellen, die sich wiederholen. Ich bereite dein Auto deshalb für die Abfahrt jeden Montag um 08:00 Uhr vor.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "Ein Schwarzes Loch ist wie ein riesiger Staubsauger im Weltall. Es hat eine super starke Anziehungskraft, die alles in seiner Nähe anzieht, sogar Licht! Stell dir vor, du hast einen Ball, den du wirfst, und er fällt immer wieder auf den Boden zurück. Bei einem Schwarzen Loch würde der Ball nie wieder zurückkommen, weil die Anziehungskraft so stark ist, dass er einfach verschwindet. Es ist also ein Ort im Weltall, wo Dinge verschwinden und nicht mehr herauskommen können.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; range_assist_battery_not_sufficient_calculated_route_one_charging_stop_no_address_slots_VP_CONCEPT
                PhraseItem(text = "Die Batterie ist nicht ausreichend geladen, um bis zum Ziel zu fahren. Aus diesem Grund habe ich eine Route mit einem Ladestopp berechnet. Du musst 30 Minuten laden und kommst dann mit voraussichtlich 21% Restladung am Ziel an.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; info_navi_remaining_time_traffic_delay_without_stopover_VP_CONCEPT
                PhraseItem(text = "Bis wir bei Berlin ankommen, dauert es ungefähr eine Stunde. Auf der Route gibt es Verkehrsbehinderungen von fünf Minuten.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; navi_traffic_information_OffRoute_traffic_delay_VP_CONCEPT
                PhraseItem(text = "Auf der schnellsten Route gibt es momentan Verkehrsverzögerungen von insgesamt fünf Minuten. Du benötigst ungefähr 30 Minuten, um das Ziel Berlin zu erreichen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "Mercedes-Benz ist bekannt für seine Innovationskraft und setzt regelmäßig neue Maßstäbe in der Automobilindustrie. Das Unternehmen investiert kontinuierlich in Forschung und Entwicklung, um fortschrittliche Technologien zu entwickeln. Dazu gehören unter anderem Elektroantriebe, autonomes Fahren und intelligente Sicherheitssysteme. Mercedes-Benz integriert modernste digitale Lösungen und vernetzte Dienste in seine Fahrzeuge, um das Fahrerlebnis zu verbessern. Durch die Kombination von traditioneller Ingenieurskunst und zukunftsweisender Technologie bleibt Mercedes-Benz ein führender Innovator und prägt die Mobilität von morgen.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; info_PILOT_conditions_VP_CONCEPT
                PhraseItem(text = "Die Navigation Guided Assist-Funktion unterstützt dich bei Geschwindigkeiten von 0 bis 130 km//h auf geschlossenen Autobahnen oder Stadtautobahnen und bei 0 bis 80 km//h auf einigen städtischen Straßen. Die Unterstützung ist jedoch nicht verfügbar in Bereichen wie Mautstationen, Raststätten, Baustellen oder statischen Hindernissen, die das System nicht erkennen kann, bei schlechtem Wetter oder in komplexen Szenarien. Weitere Informationen, einschließlich Aktivierungs- und Nutzungsbedingungen sowie Warnhinweise, findest du in der Bedienungsanleitung. Navigation Guided Assist ist jedoch kein autonomes Fahren, bitte achte stets auf die Verkehrssituation und stelle die Fahrsicherheit sicher.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "Das deutsche politische System ist eine parlamentarische Demokratie und besteht aus mehreren wichtigen Teilen. Der Bundestag ist das zentrale Parlament, in dem die Abgeordneten Gesetze verabschieden und die Regierung kontrollieren. Der Bundesrat vertritt die Interessen der Bundesländer und wirkt bei der Gesetzgebung mit. Der Bundespräsident ist das Staatsoberhaupt und hat vor allem repräsentative Aufgaben. Die Bundesregierung besteht aus dem Bundeskanzler und den Bundesministern, die die Exekutive bilden und die Politik umsetzen. Das Bundesverfassungsgericht überwacht die Einhaltung des Grundgesetzes und schützt die Rechte der Bürger. Diese Institutionen arbeiten zusammen, um die demokratische Ordnung und das Funktionieren des Staates zu gewährleisten.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; info_navi_arrival_time_with_stopovers_tomorrow_and_date_VP_CONCEPT
                PhraseItem(text = "Wir erreichen unser nächstes Ziel Hamburg morgen um 23:00 Uhr und unser finales Ziel Stuttgart am 17. Mai um 05:00 Uhr.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; main_help_overview_DEU_ENG_CONCEPT
                PhraseItem(text = "Ich erledige jederzeit Dinge für dich, wie zum Beispiel jemanden anrufen, Nachrichten schreiben, Musik spielen, oder ich beantworte deine Fragen zum Beispiel zu Fußballergebnissen oder wie das Wetter wird. Weil da mein Herz dran hängt, kenne ich mich ziemlich gut mit deinem Fahrzeug aus und mit allem was das Autofahren betrifft. Du kannst mich zum Beispiel fragen, &quot;Hey Mercedes, was für Sicherheitsassistenten gibt es hier&quot;, &quot;Hey Mercedes, fahre mich nach Hause&quot;, oder, &quot;Hey Mercedes, gibt es Stau auf meiner Strecke&quot;? Du kannst mir auch Bescheid geben wenn es dir zu warm oder zu kalt ist, wenn es dir langweilig ist oder wenn du hungrig bist. Mein Ziel ist es mich ständig weiterzuentwickeln, um dich bestmöglich zu unterstützen. Frag mich doch einfach ab und zu was ich Neues kann. Ich freue mich darauf von dir zu hören und wünsche dir jederzeit eine gute Fahrt.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "Natürlich! Hier ist die Anleitung zur Pflege eines Zitronenbaums. Wähle einen sonnigen Platz, da Zitronenbäume viel Licht brauchen. Mindestens 8 Stunden direkte Sonne pro Tag sind ideal. Verwende gut durchlässigen Boden. Eine Mischung aus Gartenerde und Kompost ist gut geeignet. Vermeide schwere, tonhaltige Böden. Halte den Boden gleichmäßig feucht, aber nicht nass. Gieße regelmäßig, besonders in trockenen Perioden. Vermeide Staunässe. Dünge den Baum während der Wachstumsperiode (Frühling und Sommer) etwa alle 4-6 Wochen mit einem speziellen Zitrusdünger. Schneide den Baum im späten Winter oder frühen Frühling, um abgestorbene oder kranke Äste zu entfernen und die Form zu erhalten. Überprüfe regelmäßig auf Schädlinge wie Blattläuse und Spinnmilben. Bei Befall mit einem geeigneten Mittel behandeln. Wenn du in einer Region mit kalten Wintern lebst, schütze den Baum vor Frost. Bringe ihn ins Haus oder in ein Gewächshaus, wenn die Temperaturen unter 5 Grad Celsius fallen. Mit diesen Tipps sollte dein Zitronenbaum gut gedeihen und dir viele leckere Früchte liefern.", type = PhraseType.PLAIN_TEXT, language = Locale.GERMAN)
            ),
            PhraseType.SSML to listOf(
// <100; ambient_light_color_yellow__status_ok_positive_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Gelb. So schön warm.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; info_ok_retrieving_data_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ok, ich schaue nach.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; navi_ask_for_state_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Wie heißt der Staat?</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; ambient_light_color_cyan__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich ändere die Farbe.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; adb_call_contact_private_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich wähle die private Nummer.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; info_navi_guidance_aborted_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich breche die Zielführung ab.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; interior_light__not_avail_for_front_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Es gibt vorne kein Innenlicht.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; info_vehiclefunctions_ev_charging_flap_already_open_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Ladeklappe ist schon offen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; calf_massage_front_seat_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich aktiviere vorne die Massage.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; temperature_rear_warmer__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich erhöhe die Temperatur hinten.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; msg_info_no_inbox_emails_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich konnte keine E-Mails im Posteingang finden.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; reading_light_front_side_on__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Verstanden, Leselicht vorne wird eingeschaltet.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; sound_experience_already_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Sound Experience ist bereits ausgeschaltet.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; vehiclefunctions_trailer_hitch_already_extended_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Anhängerkupplung ist bereits ausgeschwenkt.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; vehiclefunctions_info_dashcam_recording_not_possible_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Eine Dashcam-Aufnahme ist zurzeit nicht möglich.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; front_passenger_energizing_seat_kinetics_on__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich starte die Sitzkinetik für den Beifahrersitz.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; info_navi_switch_to_minimalist_ADR_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich habe die extra kurzen Fahrhinweise aktiviert.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; magic_sky_control__status_nok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich kann Magic Sky Control gerade nicht bedienen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; player_multiseat_playback_start_at_front_left_seat_confirmation_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Okay, die Wiedergabe vorne links wurde gestartet.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; summer_rain_on_front__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich schalte vorne Sommerregen ein. Ruht euch aus!</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; windows_open_change_singular_half_all_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Verstanden, das Fenster wird zur Hälfte geöffnet.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; info_navi_satellite_map_on_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Alles klar, die Satellitenkarte wird eingeblendet.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; media_ask_play_composer_or_artist_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Willst du den Komponisten oder den Künstler hören?</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; parking_sound_deactivated_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die akustische Einparkhilfe ist jetzt deaktiviert.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// <100; vehiclefunctions_turn_vehicle_on_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Bitte mache das Fahrzeug an, um die Funktion bedienen zu können.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; massage_program_classic_front_right_on_status_ok_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Klassische Massage beginnt jetzt vorne rechts. Genieß' die Fahrt.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; front_passenger_mobilizing_massage_on__status_ok_VP_TP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Gern. Entspannende Massage für den Beifahrersitz ist jetzt aktiv.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; ask_global_pardon_1_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Kannst du das bitte wiederholen? Ich hab’s nicht ganz mitbekommen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; autonomous_driving_driver_camera_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich kann den Drive Pilot nur bei aktivierter Fahrerkamera bedienen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; info_navi_destination_replaced_no_address_slots_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich werde das genannte Ziel austauschen und berechne die Route neu.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; info_navi_auto_zoom_not_controllable_on_display_from_seat_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Du kannst den Autozoom auf dem genannten Bildschirm von deinem Sitz aus nicht per Sprache bedienen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; info_vehicle_charging_ev_ECO_hybrid_na_QUICK_already_off_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>ECO-Laden ist in deinem Auto nicht verfügbar. Die Schnelllade-Option ist schon ausgeschaltet. Damit wird dein Auto batterieschonend geladen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; massage_program_classic_status_nok_implicit_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Leider kann ich die Klassische Massage gerade nicht starten, aber versuch doch mal deine Wirbelsäule ganz aufzurichten und deine Schultern zu entspannen. Das hilft!</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; scent_already_on_level__status_ok_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Beduftung ist bereits auf Stufe <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_parameter\">3</say-as></say-as> eingestellt.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; msg_info_no_new_emails_from_contact_combination_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich habe keine neuen E-Mails von <say-as interpret-as=\"TTS-PROPERNAME\"><say-as format=\"slot\" interpret-as=\"g_contact_combination\">Max Mustermann</say-as></say-as> gefunden.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; info_navi_arrival_time_next_day_without_stopover_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Voraussichtlich erreichst Du <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as> morgen um <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_final_destination_arrival_time\">14:00 Uhr</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// 100<x<400; range_assist_battery_sufficiently_charged_location_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Deine Batterie ist momentan ausreichend geladen, um <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as> zu erreichen. Du würdest mit voraussichtlich <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_loading_status\">45</say-as></say-as>% Batterie ankommen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; calendar_read_out_first_appointment_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Der erste Termin <say-as interpret-as=\"UNSPECIFIED\"><say-as format=\"slot\" interpret-as=\"g_title\">Zahnarzttermin</say-as></say-as> geht von <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_starttime\">10:00 Uhr</say-as></say-as> bis <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_endtime\">11:30 Uhr</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Wusstest du, dass es auf dem Jupiter Diamanten regnen könnte? Wissenschaftler glauben, dass die extremen Druck- und Temperaturbedingungen in den oberen Atmosphärenschichten des Jupiter Kohlenstoff in Diamanten verwandeln könnten. Diese Diamanten könnten dann durch die Atmosphäre fallen, wie Regen! Stell dir vor, Diamanten, die vom Himmel fallen – das ist wirklich galaktischer Luxus!</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; info_vehiclefunctions_ev_departure_time_deleted_tomorrow_no_access_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Aber so weit im Voraus kann ich nur Abfahrtszeiten einstellen, die sich wiederholen. Ich bereite dein Auto deshalb für die Abfahrt jeden <say-as interpret-as=\"UNSPECIFIED\"><say-as format=\"slot\" interpret-as=\"g_weekday\">Montag</say-as></say-as> um <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_ev_departure_time\">08:00 Uhr</say-as></say-as> vor.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ein Schwarzes Loch ist wie ein riesiger Staubsauger im Weltall. Es hat eine super starke Anziehungskraft, die alles in seiner Nähe anzieht, sogar Licht! Stell dir vor, du hast einen Ball, den du wirfst, und er fällt immer wieder auf den Boden zurück. Bei einem Schwarzen Loch würde der Ball nie wieder zurückkommen, weil die Anziehungskraft so stark ist, dass er einfach verschwindet. Es ist also ein Ort im Weltall, wo Dinge verschwinden und nicht mehr herauskommen können.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; range_assist_battery_not_sufficient_calculated_route_one_charging_stop_no_address_slots_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Batterie ist nicht ausreichend geladen, um bis zum Ziel zu fahren. Aus diesem Grund habe ich eine Route mit einem Ladestopp berechnet. Du musst <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_charging_time\">30 Minuten</say-as></say-as> laden und kommst dann mit voraussichtlich <say-as interpret-as=\"TTS-NUMBER\"><say-as format=\"slot\" interpret-as=\"g_loading_status\">21</say-as></say-as>% Restladung am Ziel an.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; info_navi_remaining_time_traffic_delay_without_stopover_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Bis wir bei <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Berlin</say-as> ankommen, dauert es ungefähr <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_final_destination_remaining_time\">eine Stunde</say-as></say-as> . Auf der Route gibt es Verkehrsbehinderungen von <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"traffic_delay_time\">fünf Minuten</say-as></say-as> .</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; navi_traffic_information_OffRoute_traffic_delay_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Auf der schnellsten Route gibt es momentan Verkehrsverzögerungen von insgesamt <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_requested_destination_traffic_delay_time\">fünf Minuten</say-as></say-as>. Du benötigst ungefähr <say-as interpret-as=\"TTS-DURATION\"><say-as format=\"slot\" interpret-as=\"g_requested_destination_driving_time\">30 Minuten</say-as></say-as>, um das Ziel <say-as format=\"slot\" interpret-as=\"g_final_destination\">Berlin</say-as> zu erreichen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Mercedes-Benz ist bekannt für seine Innovationskraft und setzt regelmäßig neue Maßstäbe in der Automobilindustrie. Das Unternehmen investiert kontinuierlich in Forschung und Entwicklung, um fortschrittliche Technologien zu entwickeln. Dazu gehören unter anderem Elektroantriebe, autonomes Fahren und intelligente Sicherheitssysteme. Mercedes-Benz integriert modernste digitale Lösungen und vernetzte Dienste in seine Fahrzeuge, um das Fahrerlebnis zu verbessern. Durch die Kombination von traditioneller Ingenieurskunst und zukunftsweisender Technologie bleibt Mercedes-Benz ein führender Innovator und prägt die Mobilität von morgen.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; info_PILOT_conditions_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Die Navigation Guided Assist-Funktion unterstützt dich bei Geschwindigkeiten von 0 bis 130 km/h auf geschlossenen Autobahnen oder Stadtautobahnen und bei 0 bis 80 km/h auf einigen städtischen Straßen. Die Unterstützung ist jedoch nicht verfügbar in Bereichen wie Mautstationen, Raststätten, Baustellen oder statischen Hindernissen, die das System nicht erkennen kann, bei schlechtem Wetter oder in komplexen Szenarien. Weitere Informationen, einschließlich Aktivierungs- und Nutzungsbedingungen sowie Warnhinweise, findest du in der Bedienungsanleitung. Navigation Guided Assist ist jedoch kein autonomes Fahren, bitte achte stets auf die Verkehrssituation und stelle die Fahrsicherheit sicher.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Das deutsche politische System ist eine parlamentarische Demokratie und besteht aus mehreren wichtigen Teilen. Der Bundestag ist das zentrale Parlament, in dem die Abgeordneten Gesetze verabschieden und die Regierung kontrollieren. Der Bundesrat vertritt die Interessen der Bundesländer und wirkt bei der Gesetzgebung mit. Der Bundespräsident ist das Staatsoberhaupt und hat vor allem repräsentative Aufgaben. Die Bundesregierung besteht aus dem Bundeskanzler und den Bundesministern, die die Exekutive bilden und die Politik umsetzen. Das Bundesverfassungsgericht überwacht die Einhaltung des Grundgesetzes und schützt die Rechte der Bürger. Diese Institutionen arbeiten zusammen, um die demokratische Ordnung und das Funktionieren des Staates zu gewährleisten.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; info_navi_arrival_time_with_stopovers_tomorrow_and_date_VP_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Wir erreichen unser nächstes Ziel <say-as format=\"slot\" interpret-as=\"g_first_intermediate_destination_info\">Hamburg</say-as> morgen um <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_first_destination_arrival_time\">23:00 Uhr</say-as></say-as> und unser finales Ziel <say-as format=\"slot\" interpret-as=\"g_final_destination_info\">Stuttgart</say-as> am <say-as interpret-as=\"TTS-DATE\"><say-as format=\"slot\" interpret-as=\"g_final_destination_date\">17. Mai</say-as></say-as> um <say-as interpret-as=\"TTS-TIME\"><say-as format=\"slot\" interpret-as=\"g_final_destination_arrival_time\">05:00 Uhr</say-as></say-as>.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; main_help_overview_DEU_ENG_CONCEPT
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Ich erledige jederzeit Dinge für dich, wie zum Beispiel jemanden anrufen, Nachrichten schreiben, Musik spielen, oder ich beantworte deine Fragen zum Beispiel zu Fußballergebnissen oder wie das Wetter wird. Weil da mein Herz dran hängt, kenne ich mich ziemlich gut mit deinem Fahrzeug aus und mit allem was das Autofahren betrifft. Du kannst mich zum Beispiel fragen, &quot;Hey Mercedes, was für Sicherheitsassistenten gibt es hier&quot;, &quot;Hey Mercedes, fahre mich nach Hause&quot;, oder, &quot;Hey Mercedes, gibt es Stau auf meiner Strecke&quot;? Du kannst mir auch Bescheid geben wenn es dir zu warm oder zu kalt ist, wenn es dir langweilig ist oder wenn du hungrig bist. Mein Ziel ist es mich ständig weiterzuentwickeln, um dich bestmöglich zu unterstützen. Frag mich doch einfach ab und zu was ich Neues kann. Ich freue mich darauf von dir zu hören und wünsche dir jederzeit eine gute Fahrt.</speak>", type = PhraseType.SSML, language = Locale.GERMAN),
// >400; LLM content
                PhraseItem(text = "<?xml version=\"1.0\"?><speak>Natürlich! Hier ist die Anleitung zur Pflege eines Zitronenbaums. Wähle einen sonnigen Platz, da Zitronenbäume viel Licht brauchen. Mindestens 8 Stunden direkte Sonne pro Tag sind ideal. Verwende gut durchlässigen Boden. Eine Mischung aus Gartenerde und Kompost ist gut geeignet. Vermeide schwere, tonhaltige Böden. Halte den Boden gleichmäßig feucht, aber nicht nass. Gieße regelmäßig, besonders in trockenen Perioden. Vermeide Staunässe. Dünge den Baum während der Wachstumsperiode (Frühling und Sommer) etwa alle 4-6 Wochen mit einem speziellen Zitrusdünger. Schneide den Baum im späten Winter oder frühen Frühling, um abgestorbene oder kranke Äste zu entfernen und die Form zu erhalten. Überprüfe regelmäßig auf Schädlinge wie Blattläuse und Spinnmilben. Bei Befall mit einem geeigneten Mittel behandeln. Wenn du in einer Region mit kalten Wintern lebst, schütze den Baum vor Frost. Bringe ihn ins Haus oder in ein Gewächshaus, wenn die Temperaturen unter 5 Grad Celsius fallen. Mit diesen Tipps sollte dein Zitronenbaum gut gedeihen und dir viele leckere Früchte liefern.</speak>", type = PhraseType.SSML, language = Locale.GERMAN)
            )
        )
    )

    // Retrieves the display names of phrase types available for a given locale. Looks up by language code
    fun getPhraseTypeDisplayNames(locale: Locale): List<String> {
        return ALL_PHRASES[locale.language]?.keys?.map { it.name.replace("_", " ") }?.sorted() ?: emptyList()
    }

    // Retrieves the list of PhraseItems for a specific locale and phrase type. Looks up by language code
    fun getPhrases(locale: Locale, phraseType: PhraseType): List<PhraseItem> {
        return ALL_PHRASES[locale.language]?.get(phraseType)?.map { it.copy() } ?: emptyList()
    }

    // Retrieves the PhraseType enum from its display name
    fun getPhraseTypeFromDisplayName(displayName: String): PhraseType? {
        return try {
            PhraseType.valueOf(displayName.replace(" ", "_"))
        } catch (e: IllegalArgumentException) {
            null
        }
    }
}
