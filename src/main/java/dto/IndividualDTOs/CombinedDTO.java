package dto.IndividualDTOs;

public class CombinedDTO {
    private FaceItDTO player;
    private FaceItDTO matches;

    public CombinedDTO(FaceItDTO dto1, FaceItDTO dto2) {
        this.player = dto1;
        this.matches = dto2;
    }
}
