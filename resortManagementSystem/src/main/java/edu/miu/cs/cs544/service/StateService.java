package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.DTO.StateDTO;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.repository.StateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper; // Example: Using ModelMapper for mapping

    public StateDTO getStateById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("State not found"));
        return convertToDTO(state);
    }

    public StateDTO createState(StateDTO stateDTO) {
        State state = convertToEntity(stateDTO);
        // Perform validations or additional logic before saving
        State savedState = stateRepository.save(state);
        return convertToDTO(savedState);
    }

    public StateDTO updateState(Long id, StateDTO stateDTO) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent()) {
            State existingState = optionalState.get();

            // Update existing state fields
            existingState.setStateName(stateDTO.getStateName());
            // Update other fields as needed

            // Save the updated state
            State updatedState = stateRepository.save(existingState);
            return modelMapper.map(updatedState, StateDTO.class);
        } else {
            // Handle the case when the state with the provided ID is not found
            return null;
        }
    }

    public boolean deleteState(Long id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent()) {
            State stateToDelete = optionalState.get();
            stateRepository.delete(stateToDelete);
            return true;
        } else {
            // Handle the case when the state with the provided ID is not found
            return false;
        }
    }

    private StateDTO convertToDTO(State state) {
        return modelMapper.map(state, StateDTO.class);
    }

    private State convertToEntity(StateDTO stateDTO) {
        return modelMapper.map(stateDTO, State.class);
    }
}
