package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.transaction.Transactional;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.ImageDataFile;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ImageDataRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageDataRepository imageRepos;

    public ImageDataService(ImageDataRepository imageRepos) {
        this.imageRepos = imageRepos;
    }

    @Transactional
    public String uploadImage(MultipartFile multipartFile) throws IOException {

        String newImageName = multipartFile.getOriginalFilename();
        Optional<ImageDataFile> existingDataFile = imageRepos.findByName(newImageName);
        if (existingDataFile.isPresent()) {
            throw new IllegalArgumentException("afbeelding met deze naam bestaat al in database");
        }
        ImageDataFile imageData = new ImageDataFile();
        imageData.setName(multipartFile.getOriginalFilename());
        imageData.setType(multipartFile.getContentType());
        imageData.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));

        ImageDataFile savedImage = imageRepos.save(imageData);

        return savedImage.getName();
    }

    @Transactional
    public byte[] getImage(String name) {

        ImageDataFile imageData = imageRepos.findByName(name).orElseThrow(() ->
                new ResourceNotFoundException("Image not found"));

        return ImageUtil.decompressImage(imageData.getImageData());
    }
}
