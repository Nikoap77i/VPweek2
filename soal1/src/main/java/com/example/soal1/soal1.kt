package com.example.soal1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val PeachLight = Color(0xFFF4B79A)
private val PeachDeep = Color(0xFFE98C68)
private val InkBlack = Color(0xFF1B1512)
private val CardBlack = Color(0xFF241B17)
private val CreamText = Color(0xFFF6ECE4)

data class LyricLine(val timestampSec: Int, val section: String, val text: String)

private val songDurationSec = 198
private val lyricLines = listOf(
    LyricLine(4, "Intro", "Yeah, they wishin' and wishin' and wishin' and wishin'"),
    LyricLine(8, "Intro", "They wishin' on me, yeah"),

    LyricLine(12, "Verse 1", "I been movin' calm, don't start no trouble with me"),
    LyricLine(15, "Verse 1", "Tryna keep it peaceful is a struggle for me"),
    LyricLine(18, "Verse 1", "Don't pull up at 6 AM to cuddle with me"),
    LyricLine(22, "Verse 1", "You know how I like it when you lovin' on me"),
    LyricLine(25, "Verse 1", "I don't wanna die for them to miss me"),
    LyricLine(28, "Verse 1", "Yes, I see the things that they wishin' on me"),
    LyricLine(31, "Verse 1", "Hope I got some brothers that outlive me"),
    LyricLine(34, "Verse 1", "They gon' tell the story, shit was different with me"),

    LyricLine(37, "Chorus", "God's plan, God's plan"),
    LyricLine(41, "Chorus", "I hold back, sometimes I won't, yeah"),
    LyricLine(45, "Chorus", "I feel good, sometimes I don't, ayy, don't"),
    LyricLine(48, "Chorus", "I finessed down Weston Road, ayy, 'nessed"),
    LyricLine(51, "Chorus", "Might go down a G-O-D, yeah, wait"),
    LyricLine(54, "Chorus", "I go hard on Southside G, yeah, wait"),
    LyricLine(57, "Chorus", "I make sure that north side eat"),
    LyricLine(60, "Chorus", "And still"),

    LyricLine(64, "Refrain", "Bad things"),
    LyricLine(66, "Refrain", "It's a lot of bad things"),
    LyricLine(69, "Refrain", "That they wishin' and wishin' and wishin' and wishin'"),
    LyricLine(73, "Refrain", "They wishin' on me"),

    LyricLine(76, "Refrain", "Bad things"),
    LyricLine(79, "Refrain", "It's a lot of bad things"),
    LyricLine(82, "Refrain", "That they wishin' and wishin' and wishin' and wishin'"),
    LyricLine(86, "Refrain", "They wishin' on me, yeah, ayy, ayy"),

    LyricLine(88, "Verse 2", "She say, \"Do you love me?\" I tell her, \"Only partly\""),
    LyricLine(91, "Verse 2", "I only love my bed and my momma, I'm sorry"),
    LyricLine(94, "Verse 2", "Fifty Dub, I even got it tatted on me"),
    LyricLine(97, "Verse 2", "81, they'll bring the crashers to the party"),
    LyricLine(101, "Verse 2", "And you know me"),
    LyricLine(103, "Verse 2", "Turn the O2 into the O3, dog"),
    LyricLine(106, "Verse 2", "Without 40, Oli', there'd be no me"),
    LyricLine(109, "Verse 2", "'Magine if I never met the broskis"),

    LyricLine(112, "Chorus", "God's plan, God's plan"),
    LyricLine(116, "Chorus", "I can't do this on my own, ayy, no, ayy"),
    LyricLine(119, "Chorus", "Someone watchin' this shit close, yep, close"),
    LyricLine(122, "Chorus", "I've been me since Scarlett Road, ayy, road, ayy"),
    LyricLine(125, "Chorus", "Might go down as G-O-D, yeah, wait"),
    LyricLine(128, "Chorus", "I go hard on Southside G, ayy, wait"),
    LyricLine(131, "Chorus", "I make sure that north side eat, yuh"),
    LyricLine(135, "Chorus", "And still"),

    LyricLine(138, "Refrain", "Bad things"),
    LyricLine(141, "Refrain", "It's a lot of bad things"),
    LyricLine(144, "Refrain", "That they wishin' and wishin' and wishin' and wishin'"),
    LyricLine(147, "Refrain", "They wishin' on me, yeah, yeah"),

    LyricLine(150, "Refrain", "Bad things"),
    LyricLine(153, "Refrain", "It's a lot of bad things"),
    LyricLine(156, "Refrain", "That they wishin' and wishin' and wishin' and wishin'"),
    LyricLine(160, "Refrain", "They wishin' on me, yeah")
)

private fun formatTime(totalSeconds: Int): String {
    val m = totalSeconds / 60
    val s = totalSeconds % 60
    return "%d:%02d".format(m, s)
}

@Composable
fun MusicDisplayScreen() {
    var currentTime by remember { mutableIntStateOf(12) }
    var isPlaying by remember { mutableStateOf(true) }
    var isLiked by remember { mutableStateOf(true) }
    val listState = rememberLazyListState()

    LaunchedEffect(isPlaying) {
        while (isPlaying && currentTime < songDurationSec) {
            delay(1000)
            currentTime++
        }
    }

    val currentLyricIndex = remember(currentTime) {
        lyricLines.indexOfLast { it.timestampSec <= currentTime }.coerceAtLeast(0)
    }
    LaunchedEffect(currentLyricIndex) {
        listState.animateScrollToItem(maxOf(0, currentLyricIndex - 1))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(PeachLight, PeachDeep)))
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Close", tint = InkBlack)
            Text("Liked Songs", fontWeight = FontWeight.Bold, color = InkBlack, fontSize = 16.sp)
            Icon(Icons.Filled.MoreVert, contentDescription = "More", tint = InkBlack)
        }

        Spacer(Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Brush.linearGradient(listOf(InkBlack, CardBlack))),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gods_plan_album_cover), // <-- Replace with your image file name
                contentDescription = "Album Art",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("God's Plan", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = InkBlack)
                Text("Drake", fontSize = 16.sp, color = InkBlack.copy(alpha = 0.75f))
            }
            IconButton(onClick = { isLiked = !isLiked }) {
                Icon(
                    if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Like",
                    tint = InkBlack
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { currentTime / songDurationSec.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .clip(RoundedCornerShape(50)),
            color = InkBlack,
            trackColor = InkBlack.copy(alpha = 0.25f)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(formatTime(currentTime), fontSize = 12.sp, color = InkBlack)
            Text("-${formatTime(songDurationSec - currentTime)}", fontSize = 12.sp, color = InkBlack)
        }

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { currentTime = (currentTime - 15).coerceAtLeast(0) }) {
                Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous", tint = InkBlack, modifier = Modifier.size(36.dp))
            }
            Spacer(Modifier.width(24.dp))
            FilledIconButton(
                onClick = { isPlaying = !isPlaying },
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = InkBlack)
            ) {
                Icon(
                    if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                    contentDescription = "Play/Pause",
                    tint = PeachLight,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(Modifier.width(24.dp))
            IconButton(onClick = { currentTime = (currentTime + 15).coerceAtMost(songDurationSec) }) {
                Icon(Icons.Filled.SkipNext, contentDescription = "Next", tint = InkBlack, modifier = Modifier.size(36.dp))
            }
        }

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(CardBlack)
                .padding(20.dp)
        ) {
            Column {
                Text("Lyrics", color = CreamText, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(Modifier.height(12.dp))
                LazyColumn(state = listState, verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    items(lyricLines) { line ->
                        val isCurrent = lyricLines.indexOf(line) == currentLyricIndex
                        Text(
                            text = line.text,
                            color = if (isCurrent) CreamText else CreamText.copy(alpha = 0.4f),
                            fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (isCurrent) 17.sp else 15.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun MusicDisplayScreenPreview() {
    MaterialTheme {
        MusicDisplayScreen()
    }
}