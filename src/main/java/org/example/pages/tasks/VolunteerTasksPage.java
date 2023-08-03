package org.example.pages.tasks;

import org.example.pages.BasePage;

import java.util.List;

import static org.example.common.CustomLogger.logger;

public class VolunteerTasksPage extends BasePage {

    //this method returns the names of the tasks on the last page
//    public List<String> getListOfTasksOnLastPage() {
//        logger.info("Waiting for the Next link is loaded");
//        new WebDriverWait(driver, Duration.ofSeconds(30))
//                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("page-link")));
//
//        //from time to time the StaleElementReferenceException is thrown so I do 2 attempts to click on the link
//        int attemptCount = 2;
//        int startIndex = 0;
//        while (startIndex < attemptCount) {
//            try {
//                logger.info("Last page link is clicked");
//                driver.findElements(By.className("page-link")).get(6).click();
//                break;
//            } catch (StaleElementReferenceException e) {
//                e.printStackTrace();
//                startIndex++;
//            }
//        }
//        //from time to time the StaleElementReferenceException is thrown if element is not loaded yet so I catch this exception
//        try {
//            logger.info("Waiting for the last page to be loaded");
//            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.attributeContains(By
//                    .xpath("//nav[@id='paginationContainer']//li[9]"), "class", "page-item disabled"));
//        } catch (StaleElementReferenceException e) {
//            e.printStackTrace();
//        }
//        logger.info("Getting the list of the tasks on the last page");
//        return getListOfTasksOnCurrentPage();
//    }
//    //this method waits until all tasks on the current page are loaded,
//    // then returns the names of the tasks on the current page as a list
//    public List<String> getListOfTasksOnCurrentPage() {
//        ArrayList<String> tasks = new ArrayList<>();
//
//        //from time to time the StaleElementReferenceException is thrown if cards are not loaded yet
//        // so I catch this exception and do one more attempt to find elements
//        int attemptCount = 2;
//        int startIndex = 0;
//        List<WebElement> searchResultContainer = null;
//        while (startIndex < attemptCount) {
//            try {
//                logger.info("Getting the list of the tasks on the current page");
//                searchResultContainer = driver.findElements(By.xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5"));
//                break;
//            } catch (StaleElementReferenceException e) {
//                e.printStackTrace();
//                startIndex++;
//            }
//        }
//        for (int i = 0; i < searchResultContainer.size(); i++) {
//            //from time to time the StaleElementReferenceException is thrown if cards are not loaded yet so I catch this exception
//            try {
//                logger.info("Getting the names of the tasks on the current page");
//                String text = driver.findElements(By.
//                        xpath("//div[@name='task-card']//div[@name='task-header']//div[@class='row']//h5")).get(i).getText();
//                tasks.add(text);
//            } catch (StaleElementReferenceException | IndexOutOfBoundsException e) {
//                e.printStackTrace();
//            }
//        } return tasks;
//    }

    public void printTheNamesOfTheTasks(List<String> taskNames) {
        for (int i = 0; i < taskNames.size(); i++) {
            logger.info("Printing the names of the tasks");
            System.out.println("Task #" + (i + 1) + ": " + taskNames.get(i));
        }
    }
}
