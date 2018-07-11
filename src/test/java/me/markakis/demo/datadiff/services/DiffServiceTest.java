package me.markakis.demo.datadiff.services;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.markakis.demo.datadiff.constants.DiffSide;
import me.markakis.demo.datadiff.domain.models.Diff;
import me.markakis.demo.datadiff.domain.repositories.DiffRepository;

/**
 * Tests functionality of DiffService class.
 * 
 * @author marqui
 */
@RunWith(EasyMockRunner.class)
public class DiffServiceTest {

    @Mock
    private DiffRepository mockDiffRepository;

    @TestSubject
    private DiffService    diffService = new DiffServiceImpl();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Tests that left side data were inserted.
     */
    @Test
    public void testSave_NewDiff_LeftSideData() {
        Integer id = 1;
        String data = "left side data";

        Optional<Diff> optional = Optional.ofNullable(null);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        expect(mockDiffRepository.save(anyObject(Diff.class))).andAnswer(
                // returns the argument as the result of save method
                () -> (Diff) EasyMock.getCurrentArguments()[0]);

        replay(mockDiffRepository);

        // actual call
        Diff leftDiff = diffService.save(id, data, DiffSide.LEFT);
        assertNotNull(leftDiff);

        assertEquals(id, leftDiff.getId());
        assertNotNull(leftDiff.getLeft());
        assertFalse(leftDiff.getLeft().trim().isEmpty());
        assertEquals(data, leftDiff.getLeft());
        assertNull(leftDiff.getRight());

        verify(mockDiffRepository);
    }

    /**
     * Tests that right side data were inserted.
     */
    @Test
    public void testSave_NewDiff_RightSideData() {
        Integer id = 2;
        String data = "right side data";

        Optional<Diff> optional = Optional.ofNullable(null);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        expect(mockDiffRepository.save(anyObject(Diff.class))).andAnswer(
                // returns the argument as the result of save method
                () -> (Diff) EasyMock.getCurrentArguments()[0]);

        replay(mockDiffRepository);

        // actual call
        Diff rightDiff = diffService.save(id, data, DiffSide.RIGHT);
        assertNotNull(rightDiff);

        assertEquals(id, rightDiff.getId());
        assertNotNull(rightDiff.getRight());
        assertFalse(rightDiff.getRight().trim().isEmpty());
        assertEquals(data, rightDiff.getRight());
        assertNull(rightDiff.getLeft());

        verify(mockDiffRepository);
    }

    /**
     * Tests that left side data were updated.
     */
    @Test
    public void testSave_UpdateDiff_LeftSideData() {
        Integer id = 3;
        String leftSide = "left side data";
        String newLeftSide = "new left side data";
        String rightSide = "right side data";

        Diff diff = new Diff(id, leftSide, rightSide);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        expect(mockDiffRepository.save(anyObject(Diff.class))).andAnswer(
                // returns the argument as the result of save method
                () -> (Diff) EasyMock.getCurrentArguments()[0]);

        replay(mockDiffRepository);

        // actual call
        Diff leftDiff = diffService.save(id, newLeftSide, DiffSide.LEFT);
        assertNotNull(leftDiff);
        assertEquals(newLeftSide, leftDiff.getLeft());

        assertEquals(id, leftDiff.getId());
        assertNotNull(leftDiff.getRight());

        verify(mockDiffRepository);
    }

    /**
     * Tests that right side data were updated.
     */
    @Test
    public void testSave_UpdateDiff_RightSideData() {
        Integer id = 4;
        String leftSide = "left side data";
        String rightSide = "right side data";
        String newRightSide = "new right side data";

        Diff diff = new Diff(id, leftSide, rightSide);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        expect(mockDiffRepository.save(anyObject(Diff.class))).andAnswer(
                // returns the argument as the result of save method
                () -> (Diff) EasyMock.getCurrentArguments()[0]);

        replay(mockDiffRepository);

        // actual call
        Diff rightDiff = diffService.save(id, newRightSide, DiffSide.RIGHT);
        assertNotNull(rightDiff);
        assertEquals(newRightSide, rightDiff.getRight());

        assertEquals(id, rightDiff.getId());
        assertNotNull(rightDiff.getLeft());

        verify(mockDiffRepository);
    }

    /**
     * Tests the result in case no data were found.
     */
    @Test
    public void testCompare_NoData() {
        Integer id = 5;

        Optional<Diff> optional = Optional.ofNullable(null);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("No available data for the requested id.", result);

        verify(mockDiffRepository);
    }

    /**
     * Tests the result in case left side data are missing.
     */
    @Test
    public void testCompare_LeftSideDataMissing() {
        Integer id = 6;
        String rightSide = "right side data";

        Diff diff = new Diff(id, null, rightSide);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("Left side data are missing.", result);

        verify(mockDiffRepository);
    }

    /**
     * Tests the result in case right side data are missing.
     */
    @Test
    public void testCompare_RightSideDataMissing() {
        Integer id = 7;
        String leftSide = "left side data";

        Diff diff = new Diff(id, leftSide, null);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("Right side data are missing.", result);

        verify(mockDiffRepository);
    }

    /**
     * Tests the result when data have different length.
     */
    @Test
    public void testCompare_DifferentLength() {
        Integer id = 8;
        String leftSide = "bGVmdCBzaWRlIGRhdGE=";
        String rightSide = "cmlnaHQgc2lkZSBkYXRhIA==";

        Diff diff = new Diff(id, leftSide, rightSide);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("Data have different length.", result);

        verify(mockDiffRepository);
    }

    /**
     * Tests the result in case data are of same length but have different
     * offsets.
     */
    @Test
    public void testCompare_SameLength_DifferentOffset() {
        Integer id = 9;
        String leftSide = "bGVmdA==";
        String rightSide = "cmlnaHQ=";

        Diff diff = new Diff(id, leftSide, rightSide);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("Data have the same length, but differ on offset(s): 0 1 2 3 4 5 6.", result);

        verify(mockDiffRepository);
    }

    /**
     * Tests the result in case data are of same length.
     */
    @Test
    public void testCompare_SameLength() {
        Integer id = 10;
        String data = "c2FtZSBkYXRhIG9uIGJvdGggc2lkZXMh";

        Diff diff = new Diff(id, data, data);
        Optional<Diff> optional = Optional.of(diff);
        expect(mockDiffRepository.findById(eq(id))).andReturn(optional);

        replay(mockDiffRepository);

        // actual call
        String result = diffService.compare(id);
        assertFalse(result.isEmpty());
        assertEquals("Data are equal.", result);

        verify(mockDiffRepository);
    }

}
