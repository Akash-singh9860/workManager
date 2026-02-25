package com.app.workmanager.core.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Formats an ISO-8601 date string into a human-readable format.
 *
 * Example: "2023-10-27T10:15:30Z" -> "Oct 27, 2023 • 10:15 AM"
 *
 * @param isoDate The date string in ISO-8601 format.
 * @return A formatted date string, or the original [isoDate] if parsing fails.
 */
fun formatPublishedDate(isoDate: String): String {
    return try {
        val parsedDate = ZonedDateTime.parse(isoDate)
        val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy • hh:mm a", Locale.getDefault())
        parsedDate.format(formatter)
    } catch (_: Exception) {
        isoDate
    }
}

/**
 * Cleans the content string by removing API-specific suffixes and trimming whitespace.
 *
 * Specifically, it removes the trailing character count pattern like "[+1234 chars]"
 * often found in news article content snippets.
 *
 * @param content The raw content string which may be null.
 * @return The cleaned content string, or a default message if [content] is null.
 */
fun cleanContent(content: String?): String {
    if (content == null) return "No content available."
    return content.replace(Regex("\\[\\+\\d+ chars\\]"), "").trim()
}
