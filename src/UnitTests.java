package playlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import java.util.LinkedList;

import org.junit.Before;

public class UnitTests {
	
	private PlaylistArrayList playlistA;
	private PlaylistLinkedList playlistB;
	private Song s1, s2, s3, s4, s5;
	
	@Before
	public void setup() {
		playlistA = new PlaylistArrayList();
		playlistB = new PlaylistLinkedList();
		s1 = new Song("Census Designated", "Jane Remover", 170, "alt/indie");
		s2 = new Song("uhoh", "Kim Petras", 360, "pop");
		s3 = new Song("Dvp", "PUP", 200, "rock");
		s4 = new Song("How to Lie", "Jane Remover", 150, "hyperpop");
		s5 = new Song("In the Dark", "Selena Gomez", 180, "pop");
	}
	
	/**
	 * Test 1 makes sure the program can properly create songs.
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		assertEquals("Census Designated", s1.getTitle());
		assertEquals("Jane Remover", s1.getArtist());
		assertEquals(170, s1.getDuration());
		assertEquals("alt/indie", s1.getGenre());
		
		assertEquals("uhoh", s2.getTitle());
		assertEquals("Kim Petras", s2.getArtist());
		assertEquals(360, s2.getDuration());
		assertEquals("pop", s2.getGenre());
		
		assertEquals("Dvp", s3.getTitle());
		assertEquals("PUP", s3.getArtist());
		assertEquals(200, s3.getDuration());
		assertEquals("rock", s3.getGenre());
	}
	
	/**
	 * Test 2 makes sure the program can properly add songs.
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		playlistA.addSong(s1);
		playlistA.addSong(s2);
		playlistA.addSong(s3);
		playlistA.addSong(s3);
		
		assertEquals(3, playlistA.getSong().size());
		assertEquals("Census Designated", playlistA.getSong().get(0).getTitle());
		assertEquals("uhoh", playlistA.getSong().get(1).getTitle());
		assertEquals("Dvp", playlistA.getSong().get(2).getTitle());
		
		// ******************************************************************************
		
		playlistB.addSong(s1);
		playlistB.addSong(s2);
		playlistB.addSong(s3);
		playlistB.addSong(s3);
		
		assertEquals(3, playlistB.getSong().size());
		assertEquals("Census Designated", playlistB.getSong().get(0).getTitle());
		assertEquals("uhoh", playlistB.getSong().get(1).getTitle());
		assertEquals("Dvp", playlistB.getSong().get(2).getTitle());
	}
	
	/**
	 * Test 3 makes sure the program can properly insert songs.
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		playlistA.addSong(s1);
		playlistA.addSong(s2);
		
		playlistA.insertSong(1, s3);
		
		assertEquals(3, playlistA.getSong().size());
		assertEquals("Dvp", playlistA.getSong().get(1).getTitle());
		
		// Rules out invalid index
		playlistA.insertSong(10, s1);
		
		assertEquals(3, playlistA.getSong().size());
		assertEquals("Census Designated", playlistA.getSong().get(0).getTitle());
		
		// ******************************************************************************
		
		playlistB.addSong(s1);
		playlistB.addSong(s2);
		
		playlistB.insertSong(1, s3);
		
		assertEquals(3, playlistB.getSong().size());
		assertEquals("Dvp", playlistB.getSong().get(1).getTitle());
		
		// Rules out invalid index
		playlistB.insertSong(10, s1);
		
		assertEquals(3, playlistB.getSong().size());
		assertEquals("Census Designated", playlistB.getSong().get(0).getTitle());
	}
	
	/**
	 * Test 4 makes sure the program can properly remove songs.
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		playlistA.addSong(s1);
		playlistA.addSong(s2);
		playlistA.addSong(s3);
		
		playlistA.removeSong(1);
		
		assertEquals(2, playlistA.getSong().size());
		assertEquals("Dvp", playlistA.getSong().get(1).getTitle());
		
		// ******************************************************************************
		
		playlistB.addSong(s1);
		playlistB.addSong(s2);
		playlistB.addSong(s3);
		
		playlistB.removeSong(1);
		
		assertEquals(2, playlistB.getSong().size());
		assertEquals("Dvp", playlistB.getSong().get(1).getTitle());
	}
	
	/**
	 * Test 5 makes sure all of the program's sorting methods work properly (Title, Artist, Duration).
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		playlistA.addSong(s1);
		
		// Rules out invalid size
		playlistA.sortByArtist();
		
		playlistA.addSong(s2);
		playlistA.addSong(s3);
		playlistA.addSong(s4);
		playlistA.addSong(s5);
		
		playlistA.sortByTitle();
		assertEquals("Census Designated", playlistA.getSong().get(0).getTitle());
		assertEquals("Dvp", playlistA.getSong().get(1).getTitle());
		assertEquals("How to Lie", playlistA.getSong().get(2).getTitle());
		assertEquals("In the Dark", playlistA.getSong().get(3).getTitle());
		assertEquals("uhoh", playlistA.getSong().get(4).getTitle());
		
		playlistA.sortByArtist();
		assertEquals("Jane Remover", playlistA.getSong().get(0).getArtist());
		assertEquals("Jane Remover", playlistA.getSong().get(1).getArtist());
		assertEquals("Kim Petras", playlistA.getSong().get(2).getArtist());
		assertEquals("PUP", playlistA.getSong().get(3).getArtist());
		assertEquals("Selena Gomez", playlistA.getSong().get(4).getArtist());
		
		playlistA.sortByDuration();
		assertEquals(360, playlistA.getSong().get(0).getDuration());
		assertEquals(200, playlistA.getSong().get(1).getDuration());
		assertEquals(180, playlistA.getSong().get(2).getDuration());
		assertEquals(170, playlistA.getSong().get(3).getDuration());
		assertEquals(150, playlistA.getSong().get(4).getDuration());
		
		// ******************************************************************************
		
		playlistB.addSong(s1);
		
		// Rules out invalid size
		playlistB.sortByArtist();
		
		playlistB.addSong(s2);
		playlistB.addSong(s3);
		playlistB.addSong(s4);
		playlistB.addSong(s5);
		
		playlistB.sortByTitle();
		assertEquals("Census Designated", playlistB.getSong().get(0).getTitle());
		assertEquals("Dvp", playlistB.getSong().get(1).getTitle());
		assertEquals("How to Lie", playlistB.getSong().get(2).getTitle());
		assertEquals("In the Dark", playlistB.getSong().get(3).getTitle());
		assertEquals("uhoh", playlistB.getSong().get(4).getTitle());
		
		playlistB.sortByArtist();
		assertEquals("Jane Remover", playlistB.getSong().get(0).getArtist());
		assertEquals("Jane Remover", playlistB.getSong().get(1).getArtist());
		assertEquals("Kim Petras", playlistB.getSong().get(2).getArtist());
		assertEquals("PUP", playlistB.getSong().get(3).getArtist());
		assertEquals("Selena Gomez", playlistB.getSong().get(4).getArtist());
		
		playlistB.sortByDuration();
		assertEquals(360, playlistB.getSong().get(0).getDuration());
		assertEquals(200, playlistB.getSong().get(1).getDuration());
		assertEquals(180, playlistB.getSong().get(2).getDuration());
		assertEquals(170, playlistB.getSong().get(3).getDuration());
		assertEquals(150, playlistB.getSong().get(4).getDuration());
	}
	
	/**
	 * Test 6 makes sure all of the program's searching methods work properly (Title, Artist, Genre).
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		playlistA.addSong(s1);
		playlistA.addSong(s2);
		playlistA.addSong(s3);
		playlistA.addSong(s4);
		playlistA.addSong(s5);
		
		ArrayList<Integer> titleResultsA = playlistA.searchByTitle("In the Dark");
		assertEquals(1, titleResultsA.size());
		assertEquals(4, titleResultsA.get(0).intValue());
		
		ArrayList<Song> artistResultsA = playlistA.searchByArtist("Jane Remover");
		assertEquals(2, artistResultsA.size());
		assertEquals(s1, artistResultsA.get(0));
		assertEquals(s4, artistResultsA.get(1));
		
		ArrayList<Song> genreResultsA = playlistA.searchByGenre("pop");
		assertEquals(2, genreResultsA.size());
		assertEquals(s2, genreResultsA.get(0));
		assertEquals(s5, genreResultsA.get(1));
		
		// ******************************************************************************
		
		playlistB.addSong(s1);
		playlistB.addSong(s2);
		playlistB.addSong(s3);
		playlistB.addSong(s4);
		playlistB.addSong(s5);
		
		LinkedList<Integer> titleResultsB = playlistB.searchByTitle("In the Dark");
		assertEquals(1, titleResultsB.size());
		assertEquals(4, titleResultsB.get(0).intValue());
		
		LinkedList<Song> artistResultsB = playlistB.searchByArtist("Jane Remover");
		assertEquals(2, artistResultsB.size());
		assertEquals(s1, artistResultsB.get(0));
		assertEquals(s4, artistResultsB.get(1));
		
		LinkedList<Song> genreResultsB = playlistB.searchByGenre("pop");
		assertEquals(2, genreResultsB.size());
		assertEquals(s2, genreResultsB.get(0));
		assertEquals(s5, genreResultsB.get(1));
	}
	
	/**
	 * Test 7 makes sure the recently played queue functions properly.
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception {
		// Rules out duplicates
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s1);
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s1);
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s2);
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s2);
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s3);
		RecentlyPlayedQueue.getInstance().addRecentlyPlayed(s3);
		
		assertEquals(3, RecentlyPlayedQueue.getInstance().getCount());
		
	}
}
