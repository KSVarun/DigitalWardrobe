import pandas as pd

from digitalwardrobe.clothing_attributes_detection.ml_src.preprocessing import get_attribute_dims, load_label_values

from digitalwardrobe.clothing_attributes_detection.ml_src.classifiers import get_pretrained_model, \
    create_attributes_model, AttributeFCN

from digitalwardrobe.clothing_attributes_detection.ml_src.classifiers import test_models

# Train and Validation Images
TRAIN_IMAGES_FOLDER = "clothing_attributes_detection/ml_src/data/ClothingAttributeDataset/train/"
VALID_IMAGES_FOLDER = "clothing_attributes_detection/ml_src/data/ClothingAttributeDataset/valid/"
labels_file = "digitalwardrobe/clothing_attributes_detection/ml_src/data/labels.csv"
label_values_file = "digitalwardrobe/clothing_attributes_detection/ml_src/data/label_values.json"

target_dims = get_attribute_dims(label_values_file)
label_values = load_label_values(label_values_file)


def test(image):
    pretrained_conv_model, _, _ = get_pretrained_model("vgg16", pop_last_pool_layer=True, use_gpu=False)
    target_dims = get_attribute_dims(label_values_file)
    attribute_models = create_attributes_model(AttributeFCN, 512, pretrained_conv_model,
                                               target_dims,
                                               "digitalwardrobe/clothing_attributes_detection/ml_src/weights/vgg16"
                                               "-fcn-266-2/",
                                               labels_file,
                                               TRAIN_IMAGES_FOLDER,
                                               VALID_IMAGES_FOLDER,
                                               num_epochs=1,
                                               is_train=False,
                                               use_gpu=False)

    results = test_models(attribute_models, pretrained_conv_model, image,
                          attribute_idx_map=label_values["idx_to_names"])
    df = pd.DataFrame(results).T
    df.columns = ["Prediction", "Pred_Index", "Confidence"]
    # df["Confidence"] = df["Confidence"].astype(float)
    df.index = df.index.str.replace("_GT", "").str.capitalize()

    predicted_data = {}
    for category, prediction in df.iterrows():
        predicted_data[category] = prediction["Confidence"]

    predicted_data["Cloth Image"] = image

    return predicted_data
