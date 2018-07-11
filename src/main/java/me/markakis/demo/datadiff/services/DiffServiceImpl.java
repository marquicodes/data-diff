package me.markakis.demo.datadiff.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import me.markakis.demo.datadiff.constants.DiffSide;
import me.markakis.demo.datadiff.domain.models.Diff;
import me.markakis.demo.datadiff.domain.repositories.DiffRepository;

/**
 * Implements the API for diffing functionalities.
 * 
 * @author marqui
 */
@Service
public class DiffServiceImpl implements DiffService {

    @Autowired
    private DiffRepository diffRepository;

    /*
     * (non-Javadoc)
     * @see
     * me.markakis.demo.datadiff.services.DiffService#save(java.lang.Integer,
     * java.lang.String, me.markakis.demo.datadiff.constants.DiffSide)
     */
    @Override
    public Diff save(Integer id, String data, DiffSide side) {
        Diff diff = diffRepository.findById(id).orElseGet(() -> new Diff(id));

        if (DiffSide.LEFT.equals(side)) {
            diff.setLeft(data);
        } else {
            diff.setRight(data);
        }

        return diffRepository.save(diff);
    }

    /*
     * (non-Javadoc)
     * @see
     * me.markakis.demo.datadiff.services.DiffService#compare(java.lang.Integer)
     */
    @Override
    public String compare(Integer id) {
        Optional<Diff> optional = diffRepository.findById(id);

        if (optional.isPresent()) {
            Diff diff = optional.get();

            if (!StringUtils.hasLength(diff.getLeft()) || diff.getLeft().trim().isEmpty()) {
                return "Left side data are missing.";
            } else if (!StringUtils.hasLength(diff.getRight()) || diff.getRight().trim().isEmpty()) {
                return "Right side data are missing.";
            }

            byte[] left = diff.getLeft().getBytes();
            byte[] right = diff.getRight().getBytes();

            if (left.length != right.length) {
                return "Data have different length.";
            }

            boolean areEqual = true;
            StringBuilder offsets = new StringBuilder("Data have the same length, but differ on offset(s):");

            for (int i = 0; i < left.length; i++) {
                if (left[i] != right[i]) {
                    areEqual = false;
                    offsets.append(" ").append(i);
                }
            }

            return areEqual ? "Data are equal." : offsets.append(".").toString();
        } else {
            return "No available data for the requested id.";
        }
    }

}
