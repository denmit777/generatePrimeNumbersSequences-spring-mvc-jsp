package org.training.generate.controller;

import org.training.generate.model.Data;
import org.training.generate.utils.SequenceGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class SequenceController {

    private static final Logger LOGGER = LogManager.getLogger(SequenceController.class.getName());

    private static final String SEQUENCES = "sequences";
    private static final String GEN_SEQUENCES = "genSequences";
    private static final String AUTO_SEQUENCES = "autoSequences";
    private static final String EMPTY_VALUE = "";

    private List<int[]> generatedSequences;

    public void setGeneratedSequences(List<int[]> generatedSequences) {
        this.generatedSequences = generatedSequences;
    }

    @GetMapping("/index")
    public String startPage() {
        return "index";
    }

    @PostMapping("/index")
    public String get5SequencesOfRandomPrimeArrays(@ModelAttribute Data data, HttpSession session) {
        int length = data.getLength();

        List<int[]> sequences = SequenceGenerator.generate5SequencesOfRandomPrimeArray(length);
        data.setAllSequences(sequences);

        setGeneratedSequences(sequences);

        Map<Integer, String> sequencesView = SequenceGenerator.convertRandomListToMap(generatedSequences);
        data.setSequencesView(sequencesView);

        LOGGER.info("Generated 5 sequences of prime numbers with a length of {} numbers in array:\n{}",
                length, data.getSequencesView());

        session.setAttribute(SEQUENCES, data.getSequencesView());

        return "redirect:/generate";
    }

    @GetMapping("/generate")
    public String generate(Model model, HttpSession session) {
        model.addAttribute(SEQUENCES, session.getAttribute(SEQUENCES));
        model.addAttribute(GEN_SEQUENCES, session.getAttribute(GEN_SEQUENCES));
        model.addAttribute(AUTO_SEQUENCES, session.getAttribute(AUTO_SEQUENCES));

        return "/generate";
    }

    @PostMapping("/get2Variants")
    public String get2VariantsOfSequences(HttpServletRequest request, @ModelAttribute Data data, HttpSession session) throws InterruptedException {

        if (request.getParameter("generate").equals("GENERATE")) {
            Map<Integer, Set<Integer>> sequencesMap = SequenceGenerator.createGenerateVariantOfSequence(generatedSequences);
            data.setGenerateSequences(sequencesMap);

            LOGGER.info("Given 5 sequences of unique prime numbers, 6 random numbers from\n" +
                            "each array after pressing the button \"GENERATE\":\n{}",
                    data.getGenerateSequences());

            session.setAttribute(GEN_SEQUENCES, data.getGenerateSequences());

            dataUpdate(session, AUTO_SEQUENCES);

            return "redirect:/generate";
        }

        if (request.getParameter("generate").equals("AUTO")) {
            SequenceGenerator.generateSequenceEvery10Sec(generatedSequences, data);

            session.setAttribute(AUTO_SEQUENCES, data.getAutoSequenceView());

            dataUpdate(session, GEN_SEQUENCES);

            return "redirect:/generate";
        }

        dataUpdate(session, GEN_SEQUENCES);
        dataUpdate(session, AUTO_SEQUENCES);

        return "redirect:/index";
    }

    private void dataUpdate(HttpSession session, String attribute) {
        session.setAttribute(SEQUENCES, EMPTY_VALUE);
        session.setAttribute(attribute, EMPTY_VALUE);
    }
}
