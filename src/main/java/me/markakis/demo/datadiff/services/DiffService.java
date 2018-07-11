package me.markakis.demo.datadiff.services;

import me.markakis.demo.datadiff.constants.DiffSide;
import me.markakis.demo.datadiff.domain.models.Diff;

/**
 * Defines the API for diffing functionalities.
 * 
 * @author marqui
 */
public interface DiffService {

    /**
     * Creates the appropriate object from the supplied information and saves it
     * into the database.
     * 
     * @param id
     *            identifier of the supplied data.
     * @param data
     *            base64 encoded data to be saved.
     * @param side
     *            the side of the comparison (left or right).
     * @return the persisted object.
     */
    Diff save(Integer id, String data, DiffSide side);

    /**
     * Performs the comparison of data correspond to the specified identifier
     * and returns its results.
     * 
     * @param id
     *            identifier used for this data comparison.
     * @return a JSON formatted String containing comparison results.
     */
    String compare(Integer id);

}
