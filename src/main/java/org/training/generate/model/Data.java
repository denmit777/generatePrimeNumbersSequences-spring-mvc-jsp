package org.training.generate.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class Data {

    private int length;
    private List<int[]> allSequences;
    private Map<Integer, String> sequencesView;
    private List<Integer> autoSequences;
    private Map<Integer, String> autoSequenceView;
    private Map<Integer, Set<Integer>> generateSequences;

    public Data() {
    }

    public Data(int length, List<int[]> allSequences, Map<Integer, String> sequencesView, List<Integer> autoSequences, Map<Integer, String> autoSequenceView, Map<Integer, Set<Integer>> generateSequences) {
        this.length = length;
        this.allSequences = allSequences;
        this.sequencesView = sequencesView;
        this.autoSequences = autoSequences;
        this.autoSequenceView = autoSequenceView;
        this.generateSequences = generateSequences;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<int[]> getAllSequences() {
        return allSequences;
    }

    public void setAllSequences(List<int[]> allSequences) {
        this.allSequences = allSequences;
    }

    public Map<Integer, String> getSequencesView() {
        return sequencesView;
    }

    public void setSequencesView(Map<Integer, String> sequencesView) {
        this.sequencesView = sequencesView;
    }

    public List<Integer> getAutoSequences() {
        return autoSequences;
    }

    public void setAutoSequences(List<Integer> autoSequences) {
        this.autoSequences = autoSequences;
    }

    public Map<Integer, String> getAutoSequenceView() {
        return autoSequenceView;
    }

    public void setAutoSequenceView(Map<Integer, String> autoSequenceView) {
        this.autoSequenceView = autoSequenceView;
    }

    public Map<Integer, Set<Integer>> getGenerateSequences() {
        return generateSequences;
    }

    public void setGenerateSequences(Map<Integer, Set<Integer>> generateSequences) {
        this.generateSequences = generateSequences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return length == data.length && Objects.equals(allSequences, data.allSequences) && Objects.equals(sequencesView, data.sequencesView) && Objects.equals(autoSequences, data.autoSequences) && Objects.equals(autoSequenceView, data.autoSequenceView) && Objects.equals(generateSequences, data.generateSequences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, allSequences, sequencesView, autoSequences, autoSequenceView, generateSequences);
    }

    @Override
    public String toString() {
        return "Data{" +
                "length=" + length +
                ", allSequences=" + allSequences +
                ", sequencesView=" + sequencesView +
                ", autoSequences=" + autoSequences +
                ", autoSequenceView=" + autoSequenceView +
                ", generateSequences=" + generateSequences +
                '}';
    }
}
